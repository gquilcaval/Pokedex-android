package com.example.practice_tablet_movil.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.databinding.FragmentDetailPokemonBinding
import com.example.practice_tablet_movil.databinding.FragmentPokemonsBinding
import com.example.practice_tablet_movil.ui.view.adapter.PokemonAdapter
import com.example.practice_tablet_movil.ui.view.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsFragment : Fragment() {

    private val pokemonViewModel: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonsBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentPokemonsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Specify layout for recycler view
        binding.recyclerViewPokemon?.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            this.context,2)


        binding.recyclerViewPokemon?.layoutManager = gridLayoutManager



        pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer{

            if (it == false){
                binding.shimerFrameLayoutList.stopShimmer()
                binding.shimerFrameLayoutList.visibility = View.GONE
                binding.recyclerViewPokemon.visibility = View.VISIBLE
            }

        })

        pokemonViewModel.listaPokemon.observe(viewLifecycleOwner, Observer {

            binding.recyclerViewPokemon?.adapter = PokemonAdapter(it)

        })
    }


}