plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    kotlin(GradlePluginId.ANDROID)
}

android {
    namespace = AppConfig.APP_CORE_ID
    setCompileSdkVersion(AppConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

}