package com.bradpark.data.datasource

import com.bradpark.domain.datasource.BookDatasource
import com.bradpark.network.retrofit.RetrofitNetworkApi
import javax.inject.Inject

class BookDatasourceImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi
): BookDatasource {
    /**
     * 책 검색 Datasource
     */
    override suspend fun getBookData(query: String, sort: String, page: Int, size: Int) =
        networkApi.getBookSearchResponse(
            query = query,
            sort = sort,
            page = page,
            size = size
        )
}