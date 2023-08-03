package com.bradpark.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.bradpark.designsystem.R
import com.bradpark.domain.model.BookData
import com.bradpark.ui.util.pagingItemHeight

/**
 * 아이템 관련 UI
 * coil 를 사용 하여 이미지 로드
 * thumbnail 라운드 처리
 * error 일 경우 이미지 표시
 * Text 가 화면 넘어갈 경우 Ellipsis 처리
 * 요구 사항 thumbnail 과 title 보여지도록 처리
 */
@Composable
fun BookPagingItem(data: BookData, onClickEvent: (BookData) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .clickable { onClickEvent(data) }, contentAlignment = Alignment.TopCenter
    ) {
        Column {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.thumbnail)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .width(100.dp)
                    .height(120.dp)
                ,
            ) {
                when (painter.state) {
                    // 이미지 로딩 표시
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator()
                    }
                    // 이미지 에러 표시
                    is AsyncImagePainter.State.Error -> {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_error_24),
                            contentDescription = null
                        )
                    }
                    // 이미지 표시
                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.width(100.dp),
                text = data.title,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * 로딩 화면
 */
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(pagingItemHeight().dp)
            .background(color = Color.Gray.copy(alpha = 0.7f)), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

/**
 * 에러 화면
 */
@Composable
fun ErrorScreen(retryEvent: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(pagingItemHeight().dp)
            .background(color = Color.Gray.copy(alpha = 0.7f)), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = ImageVector.vectorResource(R.drawable.baseline_error_24),
                contentDescription = null,
                tint = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.error_text))
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { retryEvent() }) {
                Text(text = stringResource(id = R.string.retry_text))
            }
        }
    }
}

/**
 * 미리보기 BookItem
 */
@Preview
@Composable
private fun PreviewBookPagingItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BookPagingItem(
            data = BookData(
                title = "코틀린 인 액션",
                contents = "Test",
                url = "https://test.com",
                price = 10000,
                salePrice = 9000,
                datetime = "2021-01-01",
                authors = listOf("브래드파크"),
                translators = listOf("브래드파크"),
                thumbnail = "https://test.com",
                publisher = "브래드파크",
                isbn = "1234567890",
                status = "정상판매"
            ), onClickEvent = {

            }
        )
    }
}

/**
 * 미리보기 LoadingScreen
 */
@Preview
@Composable
private fun PreviewLoadingScreen() {
    LoadingScreen()
}

/**
 * 미리보기 ErrorScreen
 */
@Preview
@Composable
private fun PreviewErrorScreen() {
    ErrorScreen()
}