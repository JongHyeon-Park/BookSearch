package com.bradpark.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bradpark.domain.model.BookData
import com.bradpark.domain.model.BookParameter
import com.bradpark.domain.repository.BookSearchRepository
import com.bradpark.domain.repository.SearchSortType
import com.bradpark.ui.util.LOAD_PAGE_SIZE
import com.bradpark.ui.util.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookListViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository
) : ViewModel() {
    private val _pageData: MutableStateFlow<MutableList<StateFlow<PagingData<BookData>>>> =
        MutableStateFlow(mutableListOf())
    val pageData = _pageData.asStateFlow()


    /**
     * 책 검색
     * @param query 검색어
     * 요구 사항 기존의 검색된 데이터는 유지하고 새로운 데이터를 추가 함
     */
    fun searchPagingData(query: String) {
        viewModelScope.launch {
            bookSearchRepository.getSearchBookData(
                BookParameter(
                    query = query,
                    sort = SearchSortType.ACCURACY,
                    size = PAGE_SIZE,
                    configPageSize = LOAD_PAGE_SIZE
                )
            ).cachedIn(viewModelScope).collect { pageData ->
                _pageData.update {
                    _pageData.value.toMutableList().apply {
                        this.add(MutableStateFlow(pageData).asStateFlow())
                    }
                }
            }
        }
    }

    /**
     * 비어 있는 데이터 제거
     */
    fun removePagingData() {
        _pageData.update {
            _pageData.value.toMutableList().apply {
                this.removeAt(_pageData.value.lastIndex)
            }
        }
    }
}