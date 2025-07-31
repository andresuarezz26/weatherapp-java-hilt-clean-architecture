plugins {
  alias(libs.plugins.android.application)
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.javaweatherapp"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.javaweatherapp"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {

  implementation(libs.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
  implementation("com.google.dagger:hilt-android:2.56.2")
  annotationProcessor("com.google.dagger:hilt-android-compiler:2.56.2")
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("io.reactivex.rxjava3:rxjava:3.1.7")
  implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
  implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
  implementation("com.google.android.gms:play-services-location:21.0.1")

  implementation("com.squareup.okhttp3:okhttp:4.11.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
  implementation("androidx.room:room-runtime:2.6.1")
  annotationProcessor("androidx.room:room-compiler:2.6.1")
  implementation("androidx.room:room-rxjava3:2.6.1")
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:4.8.1")
  testImplementation("androidx.arch.core:core-testing:2.2.0")
  testImplementation("org.mockito:mockito-inline:4.8.1")

}