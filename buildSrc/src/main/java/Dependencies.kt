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
    const val HILT = "2.44.2"
    const val RETROFIT = "2.9.0"
    const val MOSHI = "1.14.0"
    const val OKHTTP = "4.10.0"
    const val ROOM = "2.5.0"
    const val PAGING = "3.1.0"
    const val PAGING_COMPOSE = "1.0.0-alpha20"
    const val HILT_COMPOSE = "1.0.0"
    const val COIL_COMPOSE = "2.4.0"
    const val ORBIT_COMPOSE = "6.0.0"
    const val PALETTE = "1.0.0"
    const val ONEBONE = "2.3.5"
    const val NAVIGATION = "2.6.0"
}

object Modules {
    const val APP = ":app"
    const val DATA = ":core-data"
    const val NETWORK = ":core-network"
    const val DATABASE = ":core-database"
}

object BuildPluginVersion {
    const val ANDROID_BUILD_GRADLE = Versions.ANDROID_BUILD_GRADLE
    const val KOTLIN = Versions.KOTLIN
    const val HILT = Versions.HILT
}

object Kotlin {

}

object AndroidX {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val PALETTE = "androidx.palette:palette:${Versions.PALETTE}"
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

    const val ONEBONE = "me.onebone:toolbar-compose:${Versions.ONEBONE}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object Hilt {
    const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val ANDROID_CORE = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_COMPOSE}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
}

object Retrofit {
    const val CORE = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}

object Moshi {
    const val CORE = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
    const val CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
}

object Okhttp3 {
    const val CORE = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
}

object Room {
    const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val PAGING = "androidx.room:room-paging:${Versions.ROOM}"
}

object Paging {
    const val RUNTIME = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val COMPOSE = "androidx.paging:paging-compose:${Versions.PAGING_COMPOSE}"
    const val COMMON_KTX = "androidx.paging:paging-common-ktx:${Versions.PAGING}"
}

object Coil {
    const val COMPOSE = "io.coil-kt:coil-compose:${Versions.COIL_COMPOSE}"
}

object Orbit {
    const val COMPOSE = "org.orbit-mvi:orbit-compose:${Versions.ORBIT_COMPOSE}"
    const val VIEWMODEL = "org.orbit-mvi:orbit-viewmodel:${Versions.ORBIT_COMPOSE}"
}

object Navigation {
    const val COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
}