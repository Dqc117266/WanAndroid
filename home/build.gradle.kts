plugins {
    id("local.library")
}

android {
    namespace = "com.dqc.home"
}

dependencies {
    implementation(projects.base)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)
    testImplementation(libs.preview)

    testRuntimeOnly(libs.junitJupiterEngine)
}