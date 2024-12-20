plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
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
    //play-services-auth = { module = "com.google.android.gsm:play-services-auth", version.ref = "playServicesAuth" }
    //implementation(libs.play.services.auth)
    implementation(libs.play.services.auth.api.phone)
    //dataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    ///room Db
    implementation(libs.room)
    implementation(libs.room.runtime)
    ksp(libs.compiler)

    //PersianCalender
    implementation ("ir.huri:JalaliCalendar:1.3.3")
    implementation ("com.github.samanzamani:PersianDate:1.7.1")

    implementation ("androidx.work:work-runtime-ktx:2.10.0")
    implementation ("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("androidx.hilt:hilt-work:1.2.0")
    ksp ("androidx.hilt:hilt-compiler:1.2.0")
}