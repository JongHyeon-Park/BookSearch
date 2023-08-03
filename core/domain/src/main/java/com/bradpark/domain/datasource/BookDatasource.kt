package com.bradpark.domain.datasource

import com.bradpark.network.model.BookSearchResponse

interface BookDatasource {
    suspend fun getBookData(query: String, sort: String, page: Int, size: Int): BookSearchResponse
}