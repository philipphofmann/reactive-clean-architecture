package com.philipphofmann.android

import androidx.lifecycle.ViewModel
import dagger.Lazy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ViewModelFactoryTest {

    private lateinit var sut: ViewModelFactory<AViewModel>

    @Before
    fun before() {
        sut = ViewModelFactory(Lazy { AViewModel })
    }

    @Test
    fun `factory returns right view model`() {
        val actual = sut.create(AViewModel::class.java)
        assertEquals(AViewModel, actual)
    }
}

object AViewModel : ViewModel()
