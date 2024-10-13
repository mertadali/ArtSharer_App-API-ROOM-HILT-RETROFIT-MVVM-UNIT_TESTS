package com.mertadali.artsharer.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mertadali.artsharer.R
import com.mertadali.artsharer.databinding.ImageApiFragmentBinding

class ImageApiFragment : Fragment(R.layout.image_api_fragment) {

    private var imageApiFragmentBinding : ImageApiFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = ImageApiFragmentBinding.bind(view)
        imageApiFragmentBinding = binding
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    override fun onDestroyView() {
        imageApiFragmentBinding = null
        super.onDestroyView()
    }
}