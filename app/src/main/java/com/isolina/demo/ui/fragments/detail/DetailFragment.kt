package com.isolina.demo.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.isolina.demo.R
import com.isolina.demo.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var  binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setBeer(args.itemSelected)

        activity?.let {act ->
            binding.toolbar.let {
                it.title = ""
                it.setNavigationIcon(R.drawable.back)
                act.setActionBar(it)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getBeer().let { beer ->
            binding.name.text = beer.name
            binding.degrees.text = "${beer.abv} °C"
            binding.tagline.text =  beer.tagline
            binding.description.text =  beer.description
            context?.let {
                Glide.with(it).load(beer.image_url).into(binding.image)
            }
        }

    }

}