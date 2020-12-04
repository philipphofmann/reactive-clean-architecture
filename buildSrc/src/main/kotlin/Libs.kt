object Libs {
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"

    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val junit = "junit:junit:${Versions.junit}"

    // Android X test
    const val androidxTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val androidxTestCoreKtx = "androidx.test:core-ktx:${Versions.androidXTestCore}"
    const val androidxTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidXTestEspresso}"
    const val androidxTestEspressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.androidXTestEspresso}"
    const val androidxTestJUnit = "androidx.test.ext:junit:${Versions.androidXTestJUnit}"
    const val androidxTestJUnitKtx = "androidx.test.ext:junit-ktx:${Versions.androidXTestJUnit}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    const val androidxTestRules = "androidx.test:rules:${Versions.androidXTestRules}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"

    const val gradleBuildTools = "com.android.tools.build:gradle:${Versions.gradlePlugin}"

    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val lifecycleReactivestreamsKtx =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val sentry = "io.sentry:sentry-android:${Versions.sentry}"
}
