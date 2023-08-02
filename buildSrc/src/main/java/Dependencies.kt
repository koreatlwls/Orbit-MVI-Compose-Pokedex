object Versions {
    const val KOTLIN_COMPILER_EXTENSION = "1.4.7"
    const val LIFECYCLE = "2.5.0"
    const val COMPOSE_ACTIVITY = "1.7.1"
    const val COMPOSE = "1.4.3"
    const val CORE_KTX = "1.9.0"
    const val COMPOSE_MATERIAL3 = "1.2.0-alpha02"
    const val JUNIT = "4.13.2"
    const val ANDROIDX_TEST_EXT = "1.1.3"
    const val ESPRESSO_CORE = "3.4.0"
    const val ANDROID_BUILD_GRADLE = "8.0.0"
    const val KOTLIN = "1.8.21"
}

object Modules {
    const val APP = ":app"
}

object BuildPluginVersion {
    const val ANDROID_BUILD_GRADLE = Versions.ANDROID_BUILD_GRADLE
    const val KOTLIN = Versions.KOTLIN
}

object Kotlin {

}

object AndroidX {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"

}

object LifecycleLibs {
    const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
}

object Compose {
    const val FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
    const val ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
    const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
    const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3}"
    const val TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
    const val UI_UTIL = "androidx.compose.ui:ui-util:${Versions.COMPOSE}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"

}

object AndroidTestingLib {
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
}