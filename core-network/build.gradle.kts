import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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

        buildConfigField("String", "BACK_URL", getLocalProperties("BACK_URL"))
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

    Hilt.run {
        kapt(COMPILER)
        implementation(ANDROID_CORE)
    }

    Retrofit.run {
        implementation(CORE)
        implementation(CONVERTER_MOSHI)
    }

    Moshi.run {
        implementation(CORE)
        implementation(KOTLIN)
        kapt(CODEGEN)
    }

    Okhttp3.run {
        implementation(CORE)
        implementation(LOGGING_INTERCEPTOR)
    }

}

kapt {
    correctErrorTypes = true
}

fun getLocalProperties(key: String): String {
    return gradleLocalProperties(rootDir).getProperty(key)
}