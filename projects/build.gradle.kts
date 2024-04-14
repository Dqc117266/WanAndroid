plugins {
    id("local.library")
}

android {
    namespace = "com.dqc.projects"
}

dependencies {
    implementation(projects.base)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}