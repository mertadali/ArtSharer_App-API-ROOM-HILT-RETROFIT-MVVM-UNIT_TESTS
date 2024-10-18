package com.mertadali.artsharer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.R
import com.mertadali.artsharer.roomdb.ArtDbModel
import org.w3c.dom.Text
import javax.inject.Inject


class ArtFragmentAdapter @Inject constructor(val glide : RequestManager) : RecyclerView.Adapter<ArtFragmentAdapter.RowHolder>() {
    class RowHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row,parent,false)
        return RowHolder(view)

    }

    override fun getItemCount(): Int {
       return artsList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.recyclerRowImage)
        val name = holder.itemView.findViewById<TextView>(R.id.recyclerRowArtName)
        val artist = holder.itemView.findViewById<TextView>(R.id.recyclerRowArtistName)
        val year = holder.itemView.findViewById<TextView>(R.id.recyclerRowArtYear)

        val art = artsList[position]
        holder.itemView.apply {
            name.text = "Name : ${art.artName}"
            artist.text = "Artist Name :  ${art.artistName}"
            year.text = "Art Year ${art.artYear}"

            glide.load(art.imageUrl).into(imageView)


        }
    }


}