package com.bradpark.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bradpark.designsystem.component.BookDetail
import com.bradpark.domain.model.BookData

@Composable
fun SearchBookDetailRoute(bookData: BookData) {
    Box(modifier = Modifier.fillMaxSize()) {
        BookDetail(bookData = bookData)
    }
}