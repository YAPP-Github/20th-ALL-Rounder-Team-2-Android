import org.gradle.api.Project

private const val ANDROID_BASE_PLUGIN_ID = "com.android.base"
private val ANDROID_PLUGIN_IDS = listOf(
    "android",
    "android-library",
    "com.android.feature",
    "com.android.instantapp",
    "com.android.library",
    "com.android.test"
)

val Project.isAndroidProject: Boolean
    get() = plugins.hasPlugin(ANDROID_BASE_PLUGIN_ID) ||
            ANDROID_PLUGIN_IDS.any { pluginId -> plugins.hasPlugin(pluginId) }
