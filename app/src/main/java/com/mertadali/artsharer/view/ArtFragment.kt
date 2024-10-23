package com.mertadali.artsharer.view
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mertadali.artsharer.R
import com.mertadali.artsharer.adapter.ArtFragmentAdapter
import com.mertadali.artsharer.databinding.FragmentArtsBinding
import com.mertadali.artsharer.view_model.ArtAllViewModel
import javax.inject.Inject

class ArtFragment @Inject constructor(val artRecyclerAdapter : ArtFragmentAdapter ): Fragment(R.layout.fragment_arts){

    private  var fragmentArtsBinding: FragmentArtsBinding? = null

    lateinit var viewModel : ArtAllViewModel

    // Sola hareket ettirildiğinde silinmesini istiyoruz.

    private val swipeCallBack = object  : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
           val selectedArtToSwipe =  artRecyclerAdapter.artsList[layoutPosition]
            viewModel.deleteArt(selectedArtToSwipe)
        }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentArtsBinding = binding

        viewModel = ViewModelProvider(requireActivity()).get(ArtAllViewModel::class.java)

        subscribeToObservers()


        binding.recyclerViewArt.adapter = artRecyclerAdapter
        binding.recyclerViewArt.layoutManager = LinearLayoutManager(requireContext())

        // Swipe Callback
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewArt)

// fab -> Floating action button
        binding.fab.setOnClickListener {
            val action = ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun subscribeToObservers(){
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.artsList = it


        })
    }



    override fun onDestroyView() {
        fragmentArtsBinding = null
        super.onDestroyView()
    }
}