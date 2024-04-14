plugins {
    id("local.library")
}

android {
    namespace = "com.dqc.mine"
}

dependencies {
    implementation(projects.base)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)

}