package com.example.common.android.resource

import androidx.annotation.StringRes

interface ResourceProvider {
    /**
     * Loads the String with the given Id from the resources.
     *
     * @param resId Android String Id
     */
    fun getString(@StringRes resId: Int): String
}
