package com.molchanov.rickandmortyrework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.molchanov.core.App
import com.molchanov.rickandmortyrework.di.MainActivityComponent

class MainActivity : AppCompatActivity() {

    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        inject(newBase.applicationContext as App)
        super.attachBaseContext(newBase)
    }
}