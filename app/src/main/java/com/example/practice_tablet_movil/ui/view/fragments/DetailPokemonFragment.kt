package com.example.practice_tablet_movil.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practice_tablet_movil.data.network.ApiClient
import com.example.practice_tablet_movil.data.network.PokemonDataSource
import com.example.practice_tablet_movil.databinding.FragmentDetailPokemonBinding
import com.example.practice_tablet_movil.di.ServiceLocator
import com.example.practice_tablet_movil.domain.usecases.FetchPokemonUseCase
import com.example.practice_tablet_movil.extensions.getImagePath
import com.example.practice_tablet_movil.media.WrapperImage
import com.example.practice_tablet_movil.ui.viewmodel.PokemonDetailViewModel
import com.example.practice_tablet_movil.ui.viewmodel.PokemonDetailViewModelFactory


class DetailPokemonFragment : Fragment() {

    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels() {
        PokemonDetailViewModelFactory(FetchPokemonUseCase( ServiceLocator.pokemonRepository()))
    }

    private var _binding: FragmentDetailPokemonBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailPokemonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPokemon()
        setObservers()
        returnToFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getPokemon() {
        arguments?.getInt("id")?.let { pokemonDetailViewModel.getData(it) }
        arguments?.getString("id_transition")?.let {
            ViewCompat.setTransitionName(binding.imvfoto, it)
        }
    }

    private fun setObservers() {
        pokemonDetailViewModel.isLoading.observe(viewLifecycleOwner) { load ->
            if(load == false){
                binding.containerProgressbar.visibility = View.GONE
                binding.superContainer.visibility = View.VISIBLE
            }
        }

        pokemonDetailViewModel.pokemon.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvHeight.text = String.format("%.2f",it.height*0.10) + " KG"
            binding.tvWeight.text = String.format("%.2f",it.weight*0.10) + " M"
            if (it.types.size == 2){
                binding.tvType2.text =  it.types[1].type.name
            }
            binding.tvType.text = it.types[0].type.name
            binding.progressHp.progress = it.stats[0].base_stat
            binding.progressAtk.progress = it.stats[1].base_stat
            binding.progressDef.progress = it.stats[2].base_stat
            binding.progressSpd.progress = it.stats[5].base_stat
            binding.progressExp.progress = it.stats[4].base_stat
            binding.cardviewContainer.setBackgroundResource(resources.getIdentifier(it.types[0].type.name,"color",
                context?.packageName))
            WrapperImage.picasso.load(getImagePath(it.id.toInt(), 3), binding.imvfoto)
        }
    }

    private fun returnToFragment(){
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}