package com.mertadali.artsharer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.databinding.ArtRowBinding
import com.mertadali.artsharer.roomdb.ArtDbModel
import javax.inject.Inject


class ArtFragmentAdapter @Inject constructor( val glide : RequestManager) : RecyclerView.Adapter<ArtFragmentAdapter.RowHolder>() {
    class RowHolder(val binding : ArtRowBinding) : RecyclerView.ViewHolder(binding.root)

    // DiffUtil recyclerView daha verimli hale getiren bir yapı. Sadece farklılıkların olduğu kısmı güncellemeye yarar.

    private val diffUtil = object : DiffUtil.ItemCallback<ArtDbModel>(){
        override fun areItemsTheSame(oldItem: ArtDbModel, newItem: ArtDbModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtDbModel, newItem: ArtDbModel): Boolean {
            return oldItem == newItem
        }
    }
    private val diffUtilList = AsyncListDiffer(this@ArtFragmentAdapter,diffUtil)
    var artsList : List<ArtDbModel>
         get() = diffUtilList.currentList
         set(value) = diffUtilList.submitList(value)

        

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = ArtRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)

    }

    override fun getItemCount(): Int {
       return artsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val imageView = holder.binding.recyclerRowImage
        val name = holder.binding.recyclerRowArtName
        val artist = holder.binding.recyclerRowArtistName
        val year = holder.binding.recyclerRowArtYear

        var art = artsList[position]
        holder.binding.apply {
            name.text = "Name : ${art.artName}"
            artist.text = "Artist Name : ${art.artistName}"
            year.text = "Art Year :  ${art.artYear}"

            glide.load(art.imageUrl).into(imageView)


        }


    }


}