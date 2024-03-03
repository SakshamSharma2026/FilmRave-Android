package com.codewithshadow.filmrave.presentation.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithshadow.filmrave.databinding.ItemFeedHorizontalListBinding
import com.codewithshadow.filmrave.domain.models.HomeFeed
import com.codewithshadow.filmrave.domain.models.MovieResult
import com.codewithshadow.filmrave.utils.DiffUtilCallback

class HomeAdapter(
    private var onPosterClick: (movieResult: MovieResult) -> Unit
) : ListAdapter<HomeFeed, HomeAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemFeedHorizontalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFeedHorizontalListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeFeed: HomeFeed) = binding.apply {
            title.text = homeFeed.title
            // Setting up inner horizontal recyclerview

            HorizontalAdapter(
                // Poster click
                onPosterClick = onPosterClick
            ).let {
                recyclerviewPostersList.adapter = it
                it.submitList(homeFeed.list)
            }
        }
    }
}
