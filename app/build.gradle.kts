import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

val baseUrl: String by project
val movieApiKey: String by project


android {
    namespace = "com.codewithshadow.filmrave"

    compileSdk = 34

    defaultConfig {
        applicationId = "com.codewithshadow.filmrave"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        archivesName = "FilmRave.v${versionName}"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", baseUrl)
            buildConfigField("String", "MOVIE_API_KEY", movieApiKey)
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")


    //Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")


    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")


    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")


    //Retrofit2
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")


    //Image Loading
    implementation("com.github.bumptech.glide:glide:4.13.1")
    ksp("com.github.bumptech.glide:compiler:4.13.1")


    // Intuit ssp & sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")


    //Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")


    // ScrollingPagerIndicator
    implementation("ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:1.2.4")


    // VideoPlayer
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0")


    // Chrome Custom Tab
    implementation("androidx.browser:browser:1.7.0")


    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.0-beta01")


    // Airbnb lottie animation
    implementation("com.airbnb.android:lottie:5.2.0")

}