package com.codewithshadow.filmrave.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil



class DiffUtilCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}
