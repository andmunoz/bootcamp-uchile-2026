plugins {
    alias(libs.plugins.android.application)
}

    android {
    namespace = "cl.uchile.dcc.mobile.mynotes2"
        compileSdk {
            version = release(37)
        }

        defaultConfig {
      applicationId = "cl.uchile.dcc.mobile.mynotes2"
    minSdk = 35
    targetSdk = 37
    versionCode = 1
    versionName = "1.0"

    }

    buildTypes {
          release {
              optimization {
                  enable = true
                  packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
              }
          }
      }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

  dependencies {
      implementation(libs.androidx.core.ktx)
  }