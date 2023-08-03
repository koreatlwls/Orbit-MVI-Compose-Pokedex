plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    kotlin(GradlePluginId.ANDROID)
    kotlin(GradlePluginId.KAPT)
    id(GradlePluginId.HILT)
}

android {
    namespace = AppConfig.APP_CORE_ID
    setCompileSdkVersion(AppConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    TestingLib.run {
        testApi(JUNIT)
    }

    AndroidTestingLib.run {
        androidTestApi(ANDROIDX_TEST_EXT_JUNIT)
        androidTestApi(ESPRESSO_CORE)
    }

    Hilt.run {
        kapt(COMPILER)
        implementation(ANDROID_CORE)
    }

    Room.run {
        implementation(RUNTIME)
        implementation(KTX)
        kapt(COMPILER)
        implementation(PAGING)
    }

    Paging.run {
        implementation(RUNTIME)
        implementation(COMPOSE)
    }

}

kapt {
    correctErrorTypes = true
}