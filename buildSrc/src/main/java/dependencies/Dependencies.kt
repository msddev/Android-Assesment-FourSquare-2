package dependencies

import Versions

object Dependencies {

    object KotlinDep {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    }

    object JavaDep {
        const val javax = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    }

    object CoreDep {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navFragmentKtxVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navUiKtxVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtxVersion}"
    }

    object LifeCycleDep {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodelKtxVersion}"
        const val liveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtxVersion}"
        const val lifeCycleExtension =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtVersion}"
        const val lifeCycleRuntime =
            "androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycleRuntimeVersion}"
        const val lifeCycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtxVersion}"
    }

    object DaggerHiltDep {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
        const val hiltAndroidKapt =
            "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidVersion}"
    }

    object PagingDep{
        const val pagingCommon = "androidx.paging:paging-common:${Versions.pagingVersion}"
    }

    object CoroutinesDep {
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCoreVersion}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroidVersion}"
    }

    object GlideDep {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object TimberDep {
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    }

    object RetrofitDep {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val jsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"
    }

    object RoomDep {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    }

    object LottieDep {
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    }

    object LocationDep {
        const val location = "com.google.android.gms:play-services-location:${Versions.locationVersion}"
    }

    object TestDep {
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val assertJ = "org.assertj:assertj-core:${Versions.assertJVersion}"

        // required if you want to use Mockito for unit tests
        // mockito-inline library replaced the mockito-core library.
        // it is possible to mock final classes and static methods
        const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroidVersion}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"

        // mockito-kotlin is a wrapper library around Mockito
        const val mockitoKotlin =
            "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}"
        const val testRunner = "androidx.test:runner:${Versions.androidTestRunnerVersion}"
        const val testRules = "androidx.test:rules:${Versions.androidTestRuleVersion}"
        const val testCore = "androidx.test:core:${Versions.axTestCore}"
        const val testExtJunit = "androidx.test.ext:junit:${Versions.axTestJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        const val espressoIntents =
            "androidx.test.espresso:espresso-intents:${Versions.espressoVersion}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.espressoVersion}"
        const val androidxArchCore =
            "androidx.arch.core:core-testing:${Versions.androidxArchCoreVersion}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
        const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    }

}
