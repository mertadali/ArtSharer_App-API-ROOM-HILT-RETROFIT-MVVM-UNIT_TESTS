package com.mertadali.artsharer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.databinding.ImageApiRowBinding
import javax.inject.Inject

class ImageFragmentAdapter @Inject constructor(private val glide : RequestManager
     ) : RecyclerView.Adapter<ImageFragmentAdapter.RowHolderApi>() {

    class RowHolderApi(val binding : ImageApiRowBinding) : RecyclerView.ViewHolder(binding.root)


    private var onItemClickedListener : ((String) -> Unit) ? = null    // Tıklanılan görselin url almak için.

    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
           return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem == newItem

        }
    }

    private var diffUtilList = AsyncListDiffer(this@ImageFragmentAdapter,diffUtil)
    var imageList : List<String>
        get() = diffUtilList.currentList
        set(value) = diffUtilList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolderApi {
        val binding = ImageApiRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolderApi(binding)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    fun setOnItemClickedListener(listener : (String) -> Unit){
        onItemClickedListener = listener
    }

    override fun onBindViewHolder(holder: RowHolderApi, position: Int) {
      val imageView =   holder.binding.imageViewApiRow
        val url  = imageList[position]

        holder.itemView.apply {
            glide.load(url).into(imageView)

            setOnClickListener {
                onItemClickedListener?.let {
                    it(url)
                }
            }
        }




        }

    }
