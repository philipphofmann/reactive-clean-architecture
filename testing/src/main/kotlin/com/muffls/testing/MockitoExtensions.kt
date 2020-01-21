package com.muffls.testing

import org.mockito.Mockito
import org.mockito.internal.util.MockUtil

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

val Any.isMock: Boolean get() = MockUtil.isMock(this)

val Any.isSpy: Boolean get() = MockUtil.isSpy(this)
