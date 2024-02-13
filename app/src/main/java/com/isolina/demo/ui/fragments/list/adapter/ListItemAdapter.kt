package com.isolina.demo.ui.fragments.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isolina.demo.databinding.ItemListBinding
import com.isolina.demo.domain.models.Beer

class ListItemAdapter(private val onClickListener: (Beer) -> Unit) :
    ListAdapter<Beer, ListItemAdapter.ViewHolder>(DiffutilCallbackStore) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = getItem(position)
        holder.bind(beer)
        holder.itemView.setOnClickListener { onClickListener(beer) }
    }

    class ViewHolder(private val binding: ItemListBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(beer: Beer) {
            binding.name.text = beer.name
            Glide.with(context).load(beer.image_url).into(binding.image)
            binding.degrees.text = "${beer.abv} °C"
            binding.tagline.text =  beer.tagline
        }
    }

}


private object DiffutilCallbackStore: DiffUtil.ItemCallback<Beer>(){
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean =
        oldItem == newItem
}