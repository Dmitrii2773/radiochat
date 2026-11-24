pluginManagement {
    val flutterSdkPath: String = {
        val properties = java.util.Properties()
        file("local.properties").inputStream().use { properties.load(it) }
        val flutterSdkPath = properties.getProperty("flutter.sdk")
        require(flutterSdkPath != null) { "flutter.sdk not set in local.properties" }
        flutterSdkPath!!
    }()

    includeBuild("$flutterSdkPath/packages/flutter_tools/gradle")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = java.net.URI("https://artifactory-external.vkpartner.ru/artifactory/maven")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = java.net.URI("https://artifactory-external.vkpartner.ru/artifactory/maven")
        }
    }
}

plugins {
    id("dev.flutter.flutter-plugin-loader") version "1.0.0"
    id("com.android.application") version "9.1.1" apply false
    // Keep the legacy Kotlin Gradle Plugin temporarily; gradle.properties opts out
    // of AGP built-in Kotlin until all Flutter plugins are migrated.
    id("org.jetbrains.kotlin.android") version "2.3.20" apply false
}

include(":app")
