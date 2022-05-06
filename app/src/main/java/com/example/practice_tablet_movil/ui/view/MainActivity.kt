package com.example.practice_tablet_movil.ui.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.di.ServiceLocator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        ServiceLocator.destroy()
    }

}