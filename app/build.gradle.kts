plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdk = Libs.App.compileSdkVersion
    defaultConfig {
        multiDexEnabled = true
        applicationId = Libs.App.applicationId
        minSdk = Libs.App.minSdkVersion
        targetSdk = Libs.App.targetSdkVersion
        versionCode = ReleaseConfig.appVersionCode
        versionName = ReleaseConfig.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    packagingOptions {
        resources.excludes.add("/META-INF/AL2.0")
        resources.excludes.add("/META-INF/LGPL2.1")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // AndroidX/Compose/
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)

    // Accompanist
    implementation(Libs.Accompanist.systemUiController)

    // Room/DB
    implementation(Libs.AndroidX.Room.roomKtx)
    implementation(Libs.AndroidX.Room.runtime)
    kapt(Libs.AndroidX.Room.roomCompiler)

    // Navigation
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.composeNav)

    // Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.compose)
    kapt(Libs.Hilt.compiler)

    // Retrofit
    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.moshiConverter)

    // Moshi
    implementation(Libs.Network.moshi)
    kapt(Libs.Network.moshiCodegen)

    // Coil
    implementation(Libs.Coil.main)
    implementation(Libs.Coil.compose)

    // Test
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
    debugImplementation(Libs.AndroidX.Compose.uiTestManifest)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.JUnit.junit)

    // Desugaring
    coreLibraryDesugaring(Libs.Desugaring.desugarJdk)
}
