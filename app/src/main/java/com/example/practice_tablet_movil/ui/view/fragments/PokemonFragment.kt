package com.example.practice_tablet_movil.ui.view.fragments

import android.media.Image
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.data.network.ApiClient
import com.example.practice_tablet_movil.data.network.PokemonDataSource
import com.example.practice_tablet_movil.databinding.FragmentPokemonsBinding
import com.example.practice_tablet_movil.di.ServiceLocator
import com.example.practice_tablet_movil.domain.usecases.FetchAllPokemonUseCase
import com.example.practice_tablet_movil.ui.view.adapter.PokemonAdapter
import com.example.practice_tablet_movil.ui.viewmodel.PokemonViewModel
import com.example.practice_tablet_movil.ui.viewmodel.PokemonViewModelFactory



class PokemonFragment : Fragment() {

    private val pokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(FetchAllPokemonUseCase(ServiceLocator.pokemonRepository()))
    }

    private val adapter by lazy {
        PokemonAdapter(emptyList()){ id, image, idImage ->
            goDetail(id,image,idImage)
        }
    }

    private var _binding: FragmentPokemonsBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentPokemonsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        defineLayoutRecyclerView()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        pokemonViewModel.getData()
    }
    private fun defineLayoutRecyclerView() {
        val gridLayoutManager = GridLayoutManager(
            requireContext(),2)
        binding.recyclerViewPokemon?.layoutManager = gridLayoutManager
        binding.recyclerViewPokemon.adapter = adapter
    }

    private fun setObservers() {
        pokemonViewModel.listaPokemon.observe(viewLifecycleOwner){
            adapter.update(it)
        }

        pokemonViewModel.isLoading.observe(viewLifecycleOwner) { load ->
            if(load == false) {
                binding.containerProgressbar.visibility = View.GONE
                binding.recyclerViewPokemon.visibility = View.VISIBLE
            }
        }

        pokemonViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun goDetail(idPokemon: Int, image: ImageView, idImage: String) {
        val extras = FragmentNavigatorExtras(image to idImage)
        findNavController().navigate(R.id.action_pokemonsFragment_to_detailPokemonFragment, Bundle().apply {
            putInt("id", (idPokemon+1))
            putString("id_transition",idImage)
        }, null, extras)
    }

}