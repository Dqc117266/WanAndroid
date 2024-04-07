plugins {
    id("local.library")
}

android {
    namespace = "com.dqc.library_test_utils"
}

dependencies {

    implementation(libs.kotlin)
    implementation(libs.bundles.test)
    implementation(libs.bundles.compose)

    runtimeOnly(libs.junitJupiterEngine)
}