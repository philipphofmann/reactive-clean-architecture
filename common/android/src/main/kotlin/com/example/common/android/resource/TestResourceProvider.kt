package com.example.common.android.resource

class TestResourceProvider : ResourceProvider {
    override fun getString(resId: Int): String {
        return resId.toString()
    }
}
