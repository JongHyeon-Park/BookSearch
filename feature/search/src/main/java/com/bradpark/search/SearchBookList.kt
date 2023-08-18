package com.bradpark.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.bradpark.designsystem.component.BookPagingItem
import com.bradpark.designsystem.component.BookSearchBar
import com.bradpark.designsystem.component.ErrorScreen
import com.bradpark.designsystem.component.LoadingScreen
import com.bradpark.domain.model.BookData
import com.bradpark.ui.util.HideKeyboard
import com.bradpark.ui.util.pagingItemHeight
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun SearchBookListRoute(navigate: (BookData) -> Unit = {}) {
    val viewModel: SearchBookListViewModel = hiltViewModel()
    var isHideKeyboard by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        BookSearchBar(onSearch = { text ->
            viewModel.searchPagingData(text)
            isHideKeyboard = true
        })
        BookColumnItem(viewModel = viewModel) {
            navigate(it)
        }

        // 검색 시 키보드 숨김 & 포커스 제거
        if (isHideKeyboard) {
            isHideKeyboard = false
            HideKeyboard()
        }
    }
}

/**
 * 검색어 결과 리스트 UI
 * List 형태 Column + List 표시
 * 책 List 를 담고 있는 List
 * 아이템 추가 시 List 하단 으로 이동
 */
@Composable
fun BookColumnItem(
    viewModel: SearchBookListViewModel = hiltViewModel(),
    onClickEvent: (BookData) -> Unit
) {
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val pageNameData by viewModel.pageData.collectAsState()
    BookPagingEmptyCheck(viewModel = viewModel, pageData = pageNameData)
    LazyColumn(
        state = lazyColumnListState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pageNameData) { item ->
            BookRowPaging(pageData = item, onRefreshCompleteEvent = {

            },onClickEvent = onClickEvent)
        }
    }
    // LaunchedEffect 키값이 변경될 때 내부 호출
    LaunchedEffect(key1 = pageNameData.size) {
        if (pageNameData.size > 0) {
            lazyColumnListState.animateScrollToItem(pageNameData.size - 1)
        }
    }
}

/**
 * 책 리스트 UI
 * List 형태 Row + Paging 표시
 */
@Composable
fun BookRowPaging(
    pageData: StateFlow<PagingData<BookData>>,
    onRefreshCompleteEvent: () -> Unit,
    onClickEvent: (BookData) -> Unit
) {
    val paging = pageData.collectAsLazyPagingItems()

    when (paging.loadState.refresh) {
        LoadState.Loading -> {
            LoadingScreen()
        }
        is LoadState.Error -> {
            ErrorScreen {
                paging.retry()
            }
        }
        else -> {
            onRefreshCompleteEvent()
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(pagingItemHeight().dp)
                    .background(color = Color.Gray.copy(alpha = 0.7f)),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(count = paging.itemCount) { index ->
                    paging[index]?.let {
                        BookPagingItem(data = it, onClickEvent = onClickEvent)
                    }
                }
            }
        }
    }
}

/**
 * 비어있는 데이터를 체크 후 제거 처리
 */
@Composable
private fun BookPagingEmptyCheck(
    viewModel: SearchBookListViewModel = hiltViewModel(),
    pageData: MutableList<StateFlow<PagingData<BookData>>>
) {
    if (pageData.isNotEmpty()) {
        val paging = pageData[pageData.lastIndex].collectAsLazyPagingItems()
        if (paging.loadState.append.endOfPaginationReached && paging.itemCount == 0) {
            viewModel.removePagingData()
        }
    }
}


