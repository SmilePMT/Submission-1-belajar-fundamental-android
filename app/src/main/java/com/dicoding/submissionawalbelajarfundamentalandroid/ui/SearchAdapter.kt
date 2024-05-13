package com.dicoding.submissionawalbelajarfundamentalandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionawalbelajarfundamentalandroid.data.response.ItemsItem
import com.dicoding.submissionawalbelajarfundamentalandroid.databinding.ListUserBinding

class SearchAdapter : ListAdapter<ItemsItem, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {
    class SearchViewHolder(val binding: ListUserBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .skipMemoryCache(true)
                .into(binding.imgAva)
            binding.username.text = "${user.login}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}