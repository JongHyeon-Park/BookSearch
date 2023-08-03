package com.bradpark.domain.repository

import androidx.paging.PagingData
import com.bradpark.domain.model.BookData
import com.bradpark.domain.model.BookParameter
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {
    fun getSearchBookData(
        parameter: BookParameter,
    ): Flow<PagingData<BookData>>
}

enum class SearchSortType(val value: String) {
    ACCURACY("accuracy"),   // 정확도순
    RECENCY("recency"),     // 발간일순
}