package kr.co.knowledgerally.feature.image

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.knowledgerally.remote.image.ImageTranscoder

@Module
@InstallIn(SingletonComponent::class)
internal object ImageModule {

    @Provides
    fun provideImageTranscoder(@ApplicationContext context: Context): ImageTranscoder =
        SpectrumImageTranscoder(context)
}
