package com.mertadali.artsharer.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.R
import com.mertadali.artsharer.databinding.ArtDetailsFragmentBinding
import com.mertadali.artsharer.util.Status
import com.mertadali.artsharer.view_model.ArtAllViewModel
import javax.inject.Inject

class ArtDetailsFragment  @Inject constructor(val glide : RequestManager): Fragment(R.layout.art_details_fragment) {

    private var artDetailsFragmentBinding : ArtDetailsFragmentBinding? = null
    lateinit var viewModel : ArtAllViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ArtDetailsFragmentBinding.bind(view)
        artDetailsFragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity()).get(ArtAllViewModel::class.java)


        binding.imageViewDetail.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            Navigation.findNavController(view).navigate(action)
        }

        subscribeToObservers()



        // Geri işaretine basıldığında fragment arası geçiş için.
        
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveButton.setOnClickListener {
            viewModel.makeArt(
                binding.artNameDetail.text.toString(),
                binding.artistNameDetail.text.toString(),
                binding.artYearDetail.text.toString())
        }
    }

    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { StringUrl->
            artDetailsFragmentBinding?.let {
                glide.load(StringUrl).into(it.imageViewDetail)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertMessage()     //->  Error yada Loading dönmesin hata almayalım diye.

                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()

                }
                Status.LOADING ->{
                    
                }

            }
        })
    }



    override fun onDestroyView() {
        artDetailsFragmentBinding = null
        super.onDestroyView()
    }



}