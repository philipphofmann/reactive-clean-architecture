package com.muffls.monitoring.android

import com.crashlytics.android.Crashlytics
import com.muffls.monitoring.Log

@Suppress("TooManyFunctions")
class AndroidLogEngine : Log.Engine {
    override fun d(tag: String, message: String) =
        Crashlytics.log(android.util.Log.DEBUG, tag, message)

    override fun i(tag: String, message: String) =
        Crashlytics.log(android.util.Log.INFO, tag, message)

    override fun w(tag: String, message: String) =
        Crashlytics.log(android.util.Log.WARN, tag, message)

    override fun setValue(key: String, value: String) =
        Crashlytics.setString(key, value)

    override fun setValue(key: String, value: Boolean) =
        Crashlytics.setBool(key, value)

    override fun setValue(key: String, value: Int) =
        Crashlytics.setInt(key, value)

    override fun setValue(key: String, value: Float) =
        Crashlytics.setFloat(key, value)

    override fun setValue(key: String, value: Double) =
        Crashlytics.setDouble(key, value)

    override fun e(tag: String, message: String, throwable: Throwable) =
        Crashlytics.log(android.util.Log.ERROR, tag, message)

    override fun exception(throwable: Throwable) =
        Crashlytics.logException(throwable)
}
