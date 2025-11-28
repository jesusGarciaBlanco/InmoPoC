plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.components.resources)
        }
    }
}

//android {
//    namespace = "com.example.resources"
//}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.example.resources"
    generateResClass = always
}

