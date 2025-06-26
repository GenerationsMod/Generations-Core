pluginManagement.repositories {
    maven("https://maven.firstdarkdev.xyz/releases")
    gradlePluginPortal()
    maven(url = "https://maven.msrandom.net/repository/cloche")
}

plugins {
    id("com.gradle.develocity") version("3.18.2")
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

develocity.buildScan {
    termsOfUseUrl = "https://gradle.com/terms-of-service"
    termsOfUseAgree = "yes"
}

rootProject.name = "Generations-Core"