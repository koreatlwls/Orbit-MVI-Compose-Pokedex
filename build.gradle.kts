buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("${GradlePluginId.ANDROID_BUILD_GRADLE}:${BuildPluginVersion.ANDROID_BUILD_GRADLE}")
        classpath("${GradlePluginId.KOTLIN}:${BuildPluginVersion.KOTLIN}")
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