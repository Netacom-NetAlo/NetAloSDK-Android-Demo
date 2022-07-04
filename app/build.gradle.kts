plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32
    buildToolsVersion = "32.0.0"

    defaultConfig {
        applicationId = "com.netacom.netalosdkandroid"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        renderscriptTargetApi = 19
        renderscriptSupportModeEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
    }
    packagingOptions {
        excludes.add("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
        pickFirst("**/lib/**")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    signingConfigs {
        create("release") {
            storeFile = file("/Volumes/Data/Work/Netacom/SDK/NetAloSdkAndroid/demo.jks")
            storePassword = "123456"
            keyAlias = "Demo"
            keyPassword = "123456"
        }
    }
    buildTypes {
        getByName("release")  {
            signingConfig = signingConfigs.getByName("release")
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    val hiltVersion = "2.42"
    val sdkNetAloVersion = "2.4.3"
    //debugImplementation("vn.netacom:NetAloFull-Dev:$sdkNetAloVersion") //(for dev)
    implementation("vn.netacom:NetAloFull:$sdkNetAloVersion") //(for production)
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
}