package com.codewithshadow.filmrave.utils

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Toast
import com.codewithshadow.filmrave.R
import com.codewithshadow.filmrave.domain.models.Genre

fun getMovieGenreListFromIds(ids: List<Int>?): List<Genre> {
    if (ids == null) {
        return emptyList()
    }
    val results = mutableListOf<Genre>()
    ids.forEach {
        moviesGenresMap.containsKey(it) && results.add(Genre(it, moviesGenresMap[it]!!))
    }
    return results
}

fun getLanguageTitle(code: String): String {
    return when (code) {
        "en" -> "English"
        "hi" -> "Hindi"
        else -> {
            ""
        }
    }
}

fun setSpannableText(
    context: Context, spannable: Spannable, index: Int, textLength: Int, color: Int = R.color.white,
) {
    spannable.setSpan(
        StyleSpan(Typeface.BOLD), index, index + textLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )


    spannable.setSpan(
        ForegroundColorSpan(context.getColor(color)),
        index,
        index + textLength,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}


private val moviesGenresMap: HashMap<Int, String> = hashMapOf(
    28 to "Action",
    12 to "Adventure",
    16 to "Animation",
    35 to "Comedy",
    80 to "Crime",
    99 to "Documentary",
    18 to "Drama",
    10751 to "Family",
    14 to "Fantasy",
    36 to "History",
    27 to "Horror",
    10402 to "Music",
    9648 to "Mystery",
    10749 to "Romance",
    878 to "Science Fiction",
    10770 to "TV Movie",
    53 to "Thriller",
    10752 to "War",
    37 to "Western",
)

fun isNetworkAvailable(context: Context): Boolean {
    val result: Boolean
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}


fun openNetworkSetting(context: Context) {
    try {
        val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(panelIntent)
    } catch (e: Exception) {
        val panelIntent = Intent(Settings.ACTION_SETTINGS).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(panelIntent)
    }
}


fun showSharingDialog(context: Context, text: String, subTitle: String) {
    val title = "Seen \"$text\"?"
    val textWithUrl = "$title\n\n$subTitle"
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, textWithUrl)
    context.startActivity(Intent.createChooser(intent, "Share with:"))
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}