package com.example.practice_tablet_movil.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practice_tablet_movil.R
import com.example.practice_tablet_movil.databinding.FragmentDetailPokemonBinding
import com.example.practice_tablet_movil.ui.view.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailPokemonFragment : Fragment() {

    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()
    private var _binding: FragmentDetailPokemonBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailPokemonBinding.inflate(inflater,container,false)
        return binding.root

    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.getInt("id")?.let { pokemonDetailViewModel.onCreate(it) }

        pokemonDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer{

            if (it == false){
                binding.shimerFrameLayout.stopShimmer()
                binding.shimerFrameLayout.visibility = View.GONE
                binding.superContainer.visibility = View.VISIBLE
            }

        })

        pokemonDetailViewModel.pokemon.observe(viewLifecycleOwner, Observer {


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

            Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+it.id+".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imvfoto)


        })

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }
}