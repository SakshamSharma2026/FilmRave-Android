package com.codewithshadow.filmrave.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codewithshadow.filmrave.databinding.ItemBannerBinding
import com.codewithshadow.filmrave.domain.models.MovieResult
import com.codewithshadow.filmrave.utils.Constants.TMDB_IMAGE_BASE_URL_W780
import com.codewithshadow.filmrave.utils.DiffUtilCallback
import com.codewithshadow.filmrave.utils.getMovieGenreListFromIds
import com.codewithshadow.filmrave.utils.loadImage

class BannerAdapter(
) : ListAdapter<MovieResult, BannerAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResponseResult: MovieResult) = binding.apply {

            bannerTitle.text = movieResponseResult.title
            bannerGenres.text = getMovieGenreListFromIds(
                movieResponseResult.genreIds
            ).joinToString(" • ") { it.name }

            bannerImage.loadImage(TMDB_IMAGE_BASE_URL_W780.plus(movieResponseResult.backdropPath))

        }
    }
}
