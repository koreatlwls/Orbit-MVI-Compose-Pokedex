buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("${GradlePluginId.ANDROID_BUILD_GRADLE}:${BuildPluginVersion.ANDROID_BUILD_GRADLE}")
        classpath("${GradlePluginId.KOTLIN}:${BuildPluginVersion.KOTLIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}