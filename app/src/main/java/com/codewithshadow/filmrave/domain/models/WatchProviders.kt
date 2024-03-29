package com.codewithshadow.filmrave.domain.models


data class WatchProviders(
    var id: Int? = null,
    var results: Results? = null
)


data class Results(
    var iN: IN? = null
)

data class IN(
    val link: String?,
)