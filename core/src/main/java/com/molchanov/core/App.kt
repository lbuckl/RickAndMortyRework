package com.molchanov.core

import com.molchanov.core.di.ApplicationProvider

interface App {
    fun getApplicationProvider(): ApplicationProvider
}