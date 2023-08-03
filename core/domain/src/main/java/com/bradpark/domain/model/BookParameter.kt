package com.bradpark.domain.model

import com.bradpark.domain.repository.SearchSortType

data class BookParameter(
    val query: String,
    val sort: SearchSortType,
    val size: Int,
    val configPageSize: Int
)
