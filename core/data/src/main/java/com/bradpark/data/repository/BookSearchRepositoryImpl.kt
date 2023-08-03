package com.bradpark.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bradpark.data.paging.BookPagingSource
import com.bradpark.domain.datasource.BookDatasource
import com.bradpark.domain.model.BookData
import com.bradpark.domain.model.BookParameter
import com.bradpark.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookDatasource: BookDatasource
): BookSearchRepository {

    /**
     * 책 검색
     */
    override fun getSearchBookData(parameter: BookParameter): Flow<PagingData<BookData>> {
        return Pager(PagingConfig(pageSize = parameter.configPageSize)) {
            BookPagingSource(bookDatasource = bookDatasource, parameter = parameter)
        }.flow
    }

}