package kr.co.knowledgerally.feature.image

import android.content.Context
import android.net.Uri
import com.facebook.spectrum.DefaultPlugins
import com.facebook.spectrum.EncodedImageSink
import com.facebook.spectrum.EncodedImageSource
import com.facebook.spectrum.Spectrum
import com.facebook.spectrum.image.EncodedImageFormat
import com.facebook.spectrum.image.ImageSize
import com.facebook.spectrum.logging.SpectrumLogcatLogger
import com.facebook.spectrum.options.TranscodeOptions
import com.facebook.spectrum.requirements.EncodeRequirement
import com.facebook.spectrum.requirements.ResizeRequirement.Mode
import kr.co.knowledgerally.core.exception.ImageTranscodeException
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.remote.image.Image
import kr.co.knowledgerally.remote.image.ImageTranscoder
import java.io.ByteArrayOutputStream

class SpectrumImageTranscoder(private val context: Context) : ImageTranscoder {

    override fun from(uriString: String): Result<Image> {
        val result = runCatching {
            val uri = Uri.parse(uriString)
            val spectrum = Spectrum.make(SpectrumLogcatLogger(), DefaultPlugins.get())
            val outputStream = ByteArrayOutputStream()
            val inputStream = context.contentResolver.openInputStream(uri)
            val options = TranscodeOptions.Builder(
                EncodeRequirement(EncodedImageFormat.JPEG, IMAGE_QUALITY)
            )
                .resize(Mode.EXACT_OR_SMALLER, ImageSize(IMAGE_SIZE, IMAGE_SIZE))
                .build()

            spectrum.transcode(
                EncodedImageSource.from(inputStream),
                EncodedImageSink.from(outputStream),
                options,
                context.packageName
            )
            Image(
                data = outputStream.toByteArray(),
                filename = UriUtil.getFilepath(context, uri)
            )
                .also { Logger.d("SpectrumImageTranscoder", "image: $it") }
        }

        val exception = result.exceptionOrNull() ?: return result
        return Result.failure(ImageTranscodeException(uriString, exception))
    }

    companion object {
        private const val IMAGE_SIZE = 1024
        private const val IMAGE_QUALITY = 80
    }
}
