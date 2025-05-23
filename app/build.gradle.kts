plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.gms.google.services)
    id ("kotlin-parcelize")
}

android {
    namespace = "salimi.mohamad.aragenejetpack"
    compileSdk = 35

    defaultConfig {
        applicationId = "salimi.mohamad.aragenejetpack"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //HILT Di
    implementation(libs.android.hilt)
    ksp(libs.kapt.hilt)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)

    implementation(libs.coil.compose)
   // implementation(libs.play.services.auth.api.phone)

    //dataStore
    implementation(libs.androidx.datastore.preferences)
    implementation (libs.androidx.work.runtime.ktx)

    ///room Db
    implementation(libs.room)
    implementation(libs.room.runtime)
    ksp(libs.compiler)

    //PersianCalender
    implementation (libs.jalalicalendar)
    implementation (libs.persiandate)
    
    //HiltLIbrary
    implementation (libs.androidx.work.runtime.ktx)
    implementation (libs.android.hilt)
    ksp(libs.kapt.hilt)
    implementation (libs.androidx.hilt.work)
    ksp (libs.androidx.hilt.compiler)

    //FirebaseLibrary
    implementation (libs.firebase.inappmessaging.display)
    implementation (libs.firebase.analytics)

    //AparatViewLibrary
    implementation(libs.aparat.view)

    implementation ("com.airbnb.android:lottie-compose:6.3.0")


    implementation ("com.google.android.gms:play-services-auth:21.0.0")
    implementation ("com.google.android.gms:play-services-auth-api-phone:18.0.2")

}