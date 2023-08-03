package com.bradpark.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bradpark.data.mapper.asData
import com.bradpark.domain.datasource.BookDatasource
import com.bradpark.domain.model.BookData
import com.bradpark.domain.model.BookParameter
import javax.inject.Inject

/**
 * Book 데이터 페이징 소스
 */
class BookPagingSource @Inject constructor(
    private val bookDatasource: BookDatasource,
    private val parameter: BookParameter
) : PagingSource<Int, BookData>() {
    override fun getRefreshKey(state: PagingState<Int, BookData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookData> {
        return try {
            var nextPage: Int? = params.key ?: 1
            bookDatasource.getBookData(
                query = parameter.query,
                sort = parameter.sort.value,
                page = params.key ?: 1,
                size = parameter.size
            ).let {
                //마지막 페이지인 경우 null 처리 (Paging3 에서는 null 처리를 하지 않으면 무한 스크롤이 되지 않음)
                nextPage = if (it.meta.isEnd) {
                    null
                } else {
                    nextPage?.plus(1)
                }

                //요구 사항 정상판매 데이터만 보여지도록 설정
                LoadResult.Page(
                    data = it.documents.filter { document -> document.status == BookStatus.NORMAL.value }
                        .map { data -> data.asData() },
                    prevKey = null,
                    nextKey = nextPage
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

enum class BookStatus(val value: String) {
    NORMAL("정상판매")
}