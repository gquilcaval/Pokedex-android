package com.example.practice_tablet_movil.ui.view

import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.practice_tablet_movil.databinding.ActivityMainBinding
import com.example.practice_tablet_movil.ui.view.adapter.PokemonAdapter
import com.example.practice_tablet_movil.ui.view.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)








    }


}