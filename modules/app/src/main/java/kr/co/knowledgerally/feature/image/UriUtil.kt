package kr.co.knowledgerally.feature.image

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import kr.co.knowledgerally.log.Logger
import java.io.File

object UriUtil {

    fun getFilepath(context: Context, uri: Uri): String = runCatching<String> {
        when {
            uri.path?.startsWith("/storage") == true -> uri.path!!
            File(uri.toString()).extension.isNotEmpty() -> uri.toString()
            else -> {
                val columns = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
                context.contentResolver.query(uri, columns, null, null, null)
                    ?.use { cursor ->
                        cursor.moveToFirst()
                        val columnIndex =
                            cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
                        cursor.getString(columnIndex)
                    } ?: ""
            }
        }
    }
        .onFailure { Logger.e("UriResolver", "getFilePath($uri)") }
        .getOrDefault("")
}
