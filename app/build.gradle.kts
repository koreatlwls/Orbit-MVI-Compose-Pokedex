plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    kotlin(GradlePluginId.ANDROID)
}

android {
    namespace = AppConfig.APP_ID
    setCompileSdkVersion(AppConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AppConfig.APP_ID
        minSdk = AppConfig.MIN_SDK_VERSION
        targetSdk = AppConfig.COMPILE_SDK_VERSION
        versionCode = AppConfig.APP_VERSION_CODE
        versionName = AppConfig.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    AndroidX.run {
        implementation(ANDROIDX_CORE_KTX)
    }

    LifecycleLibs.run {
        implementation(RUNTIME_KTX)
    }

    Compose.run {
        api(UI)
        api(MATERIAL)
        api(MATERIAL3)
        api(ACTIVITY)
        api(FOUNDATION)
        api(TOOLING_PREVIEW)
        api(UI_UTIL)

        debugApi(TEST_MANIFEST)
        debugApi(TOOLING_PREVIEW)
    }

    testApi(TestingLib.JUNIT)

    AndroidTestingLib.run {
        androidTestApi(ANDROIDX_TEST_EXT_JUNIT)
        androidTestApi(ESPRESSO_CORE)
        androidTestApi(COMPOSE_UI_TEST_JUNIT4)
    }

}