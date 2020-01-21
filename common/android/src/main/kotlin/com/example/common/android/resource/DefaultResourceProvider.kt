package com.example.common.android.resource

import android.content.Context
import androidx.annotation.StringRes

/**
 * Gets the resources from the strings.xml files.
 *
 * Created by philipphofmann on 25/11/2016.
 */

class DefaultResourceProvider(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}
