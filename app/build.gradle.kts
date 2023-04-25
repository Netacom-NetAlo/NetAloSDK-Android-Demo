plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.netacom.netalosdkandroid"
    compileSdk = 33
    buildToolsVersion = "33.0.0"
    defaultConfig {
        applicationId = "com.netacom.netalosdkandroid"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {
    val sdkChatVersion = "1.0.4"
    //implementation("vn.chat-sdk:chat-dev:$sdkChatVersion") //(for dev)
    implementation("vn.chat-sdk:chat:$sdkChatVersion") //(for production)
    implementation("com.google.android.material:material:1.8.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.1.2")
}