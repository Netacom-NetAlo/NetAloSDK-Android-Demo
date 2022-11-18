dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "NetAloSDKAndroid"
include(":app")
 