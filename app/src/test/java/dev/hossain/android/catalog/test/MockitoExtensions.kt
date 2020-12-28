package dev.hossain.android.catalog.test

import org.mockito.Mockito

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)
