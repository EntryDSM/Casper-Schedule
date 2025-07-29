rootProject.name = "Casper-Schedule"

pluginManagement {
    includeBuild("casper-convention")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("casper-schedule")
