package com.mertadali.artsharer.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mertadali.artsharer.R
import com.mertadali.artsharer.adapter.ImageFragmentAdapter
import com.mertadali.artsharer.databinding.ImageApiFragmentBinding
import com.mertadali.artsharer.util.Status
import com.mertadali.artsharer.view_model.ArtAllViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiFragment @Inject constructor(val apiRecyclerAdapter : ImageFragmentAdapter): Fragment(R.layout.image_api_fragment) {

    lateinit var viewModel : ArtAllViewModel
    private var imageApiFragmentBinding : ImageApiFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = ImageApiFragmentBinding.bind(view)
        imageApiFragmentBinding = binding
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity()).get(ArtAllViewModel::class.java)


        // Search Text işlemleri

        var job : Job? = null

        binding.sarchImageApi.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                   if (it.toString().isNotEmpty()){
                       viewModel.searchForImage(it.toString())
                   }
                }
            }

        }

        subscribeToObserver()

        binding.recyclerViewApi.adapter = apiRecyclerAdapter
        binding.recyclerViewApi.layoutManager = GridLayoutManager(requireContext(),4)


        apiRecyclerAdapter.setOnItemClickedListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

       private fun subscribeToObserver(){
           viewModel.imageList.observe(viewLifecycleOwner, Observer {
               when(it.status){
                   Status.SUCCESS -> {
                       Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
                       val urls = it.data?.hits?.map { imageResultList -> imageResultList.previewURL }


                       apiRecyclerAdapter.imageList = urls ?: listOf()
                       imageApiFragmentBinding?.progressForApi?.visibility = View.GONE

                   }
                   Status.LOADING -> {
                       imageApiFragmentBinding?.progressForApi?.visibility = View.VISIBLE

                   }
                   Status.ERROR -> {
                       Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()

                       imageApiFragmentBinding?.progressForApi?.visibility = View.GONE


                   }

               }

           })

        }






    override fun onDestroyView() {
        imageApiFragmentBinding = null
        super.onDestroyView()
    }
}