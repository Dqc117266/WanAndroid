pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()

        maven { url = uri("https://jitpack.io" )}
        maven { url = uri("https://maven.aliyun.com/repository/releases" )}
        maven { url = uri("https://maven.aliyun.com/repository/jcenter" )}
        maven { url = uri("https://maven.aliyun.com/repository/google" )}
        maven { url = uri("https://maven.aliyun.com/repository/central" )}
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin" )}
        maven { url = uri("https://maven.aliyun.com/repository/public" )}

    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
