object Config {
    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31
}

object Modules {
    const val App = ":app"
    const val Base = ":base"
    const val Data = ":data"
    const val History = ":history"
    const val Model = ":model"
    const val Utils = ":utils"
}

object Versions {
    const val retrofit = "2.9.0"
    const val okHttpVersion = "4.9.2"
    const val ktx = "1.7.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val swipeRefreshLayout = "1.1.0"
    const val coroutines = "1.5.0"
    const val coroutinesAdapter = "0.9.2"
    const val koin = "3.1.2"
    const val coil = "1.2.1"
    const val room = "2.3.0"
    const val recycle = "1.2.1"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
}

object Android {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val Material = "com.google.android.material:material:${Versions.material}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val SwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val Recycle = "androidx.recyclerview:recyclerview:${Versions.recycle}"
}

object Kotlin {
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val CoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val CoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
}

object Koin {
    const val Core = "io.insert-koin:koin-core:${Versions.koin}"
    const val Android = "io.insert-koin:koin-android:${Versions.koin}"
    const val AndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
}

object Coil {
    const val Coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val Core = "androidx.room:room-runtime:${Versions.room}"
    const val Compiler = "androidx.room:room-compiler:${Versions.room}"
    const val Ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Tests {
    const val JUnit = "junit:junit:4.13.2"
    const val AndroidJUnit = "androidx.test.ext:junit:1.1.3"
    const val Espresso = "androidx.test.espresso:espresso-core:3.4.0"
}
