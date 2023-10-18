plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.lantt.moviescatalog"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.lantt.moviescatalog"
        minSdk = 26
        //noinspection OldTargetApi
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val navigationVersion = "2.5.3"
    val accompanistVersion = "0.28.0"
    val material3Version = "1.1.1"
    val koinVersion = "3.3.2"
    val koinComposeVersion = "3.4.1"
    val retrofitVersion = "2.9.0"
    val gsonVersion = "2.10.1"
    val gsonConverterVersion = "2.9.0"
    val securityVersion = "1.1.0-alpha06"
    val loggingInterceptorVersion = "3.9.0"

    val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"
    val accompanist = "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    val material3 = "androidx.compose.material3:material3:$material3Version"
    val koinCore = "io.insert-koin:koin-core:$koinVersion"
    val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
    val koinAndroidCompose = "io.insert-koin:koin-androidx-compose:$koinComposeVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val gson = "com.google.code.gson:gson:$gsonVersion"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$gsonConverterVersion"
    val security = "androidx.security:security-crypto:$securityVersion"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(navigationCompose)
    implementation(accompanist)
    implementation(material3)
    implementation(koinCore)
    implementation(koinAndroid)
    implementation(koinAndroidCompose)
    implementation(retrofit)
    implementation(gson)
    implementation(gsonConverter)
    implementation(security)
    implementation(loggingInterceptor)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}