package com.mertadali.artsharer.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.R
import com.mertadali.artsharer.databinding.ArtDetailsFragmentBinding
import javax.inject.Inject

class ArtDetailsFragment  @Inject constructor(val glide : RequestManager): Fragment(R.layout.art_details_fragment) {

    private var artDetailsFragmentBinding : ArtDetailsFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ArtDetailsFragmentBinding.bind(view)
        artDetailsFragmentBinding = binding

        binding.imageViewDetail.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            Navigation.findNavController(view).navigate(action)
        }
        
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    override fun onDestroyView() {
        artDetailsFragmentBinding = null
        super.onDestroyView()
    }



}