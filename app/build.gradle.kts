    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.jetbrains.kotlin.android)
        id("com.google.devtools.ksp")
        id("com.google.dagger.hilt.android")
        id ("androidx.navigation.safeargs.kotlin")
    }

    android {
        namespace = "com.mertadali.artsharer"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.mertadali.artsharer"
            minSdk = 21
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


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
        buildFeatures{
            viewBinding = true

            //noinspection DataBindingWithoutKapt
            dataBinding = true
        }
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)


        //   --------- HILT----------
        // ->  https://developer.android.com/build/migrate-to-ksp,     https://developer.android.com/training/dependency-injection/hilt-android
        val hiltVersion = "2.51.1"
        implementation("com.google.dagger:hilt-android:$hiltVersion")
        ksp ("com.google.dagger:hilt-compiler:$hiltVersion")




        // ---------- COROUTINES----------    -> https://github.com/Kotlin/kotlinx.coroutines
        val coroutineVersion = "1.9.0"
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")


        //  ------ LIFE CYCLE-------  -> https://developer.android.com/jetpack/androidx/releases/lifecycle
        val lifecycleVersion = "2.8.6"
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")   // -> ViewModel
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")  // -> LiveData
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")  // -> Lifecycles only (without ViewModel or LiveData)
        testImplementation("androidx.arch.core:core-testing:2.2.0")         // optional - Test helpers for LiveData
        testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycleVersion")  // optional - Test helpers for Lifecycle runtime



        // ---- ROOM DATABASE------  -> https://developer.android.com/jetpack/androidx/releases/room
        val roomVersion = "2.6.1"
        implementation("androidx.room:room-runtime:$roomVersion")
        ksp("androidx.room:room-compiler:$roomVersion")
        implementation("androidx.room:room-ktx:$roomVersion")     // ->  optional - Kotlin Extensions and Coroutines support for Room



        // ------ RETROFIT ------    ->  https://medium.com/@KaushalVasava/retrofit-in-android-5a28c8e988ce
        val retrofitVersion = "2.11.0"
        implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
        implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")


        //   -------- NAVIGATION -------  ->  https://developer.android.com/jetpack/androidx/releases/navigation
        val navVersion = "2.8.2"
        implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
        implementation("androidx.navigation:navigation-ui-ktx:$navVersion")


        // ------GLIDE---------       ->      https://github.com/bumptech/glide
        implementation ("com.github.bumptech.glide:glide:4.16.0")
        ksp("com.github.bumptech.glide:compiler:4.16.0")


        // TestImplementations
        implementation ("androidx.test:core:1.6.1")
        testImplementation ("org.hamcrest:hamcrest-all:1.3")
        testImplementation ("androidx.arch.core:core-testing:2.2.0")
        testImplementation ("org.robolectric:robolectric:4.8.1")
        testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
        testImplementation ("com.google.truth:truth:1.4.4")
        testImplementation ("org.mockito:mockito-core:4.7.0")


        // Android Test Implementations
        androidTestImplementation ("org.mockito:mockito-android:4.7.0")
        androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
        androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
        androidTestImplementation ("com.google.truth:truth:1.4.4")
        androidTestImplementation ("org.mockito:mockito-core:4.7.0")
        androidTestImplementation ("com.google.dagger:hilt-android-testing:2.51.1")
        kspAndroidTest ("com.google.dagger:hilt-android-compiler:2.51.1")
        debugImplementation ("androidx.fragment:fragment-testing:1.8.4")
        androidTestImplementation("androidx.test.espresso:espresso-contrib:3.6.1")




    }



