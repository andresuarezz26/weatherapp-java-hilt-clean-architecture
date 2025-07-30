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
}