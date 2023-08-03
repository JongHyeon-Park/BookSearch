package com.bradpark.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bradpark.designsystem.R
import com.bradpark.domain.model.BookData
import java.text.NumberFormat

/**
 * 상세 화면 UI
 * coil 를 사용 하여 이미지 로드
 * thumbnail 라운드 처리
 * error 일 경우 기본 이미지 표시
 * Text 가 화면 넘어갈 경우 Ellipsis 처리
 * 요구 사항 thumbnail, title, author, content, price, salePrice 보여지도록 처리
 */
@Composable
fun BookDetail(bookData: BookData) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(150.dp)
                .height(170.dp)
                .align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalContext.current)
                .data(bookData.thumbnail)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.baseline_android_48),
            error = painterResource(id = R.drawable.baseline_error_24)
        )
        Spacer(modifier = Modifier.height(8.dp))
        BookInfoText(stringResource(id = R.string.book_title), bookData.title)
        Spacer(modifier = Modifier.height(8.dp))
        BookInfoText(
            stringResource(id = R.string.author_title),
            bookData.authors.joinToString { it })  // 저자가 여러명일 경우 , 로 구분
        Spacer(modifier = Modifier.height(8.dp))
        BookInfoText(stringResource(id = R.string.published_date), bookData.datetime)
        Spacer(modifier = Modifier.height(8.dp))
        BookInfoText(stringResource(id = R.string.book_content), bookData.contents)
        Spacer(modifier = Modifier.height(8.dp))
        PriceText(bookData)
    }

}

/**
 * 가격 표시
 * salePrice 가 0 이 아닐 경우 salePrice 표시
 */
@Composable
private fun PriceText(bookData: BookData) {
    if (bookData.price != 0) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.book_price))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                text = stringResource(
                    id = R.string.price_unit,
                    NumberFormat.getInstance().format(bookData.price)
                ),
                style = if (bookData.salePrice != 0) LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough) else LocalTextStyle.current
            )
            if (bookData.salePrice != 0) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_forward_24),
                    contentDescription = null,
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically),
                    text = stringResource(
                        id = R.string.price_unit,
                        NumberFormat.getInstance().format(bookData.salePrice)
                    ),
                )
            }
        }

    }
}

/**
 * 책 정보 표시
 */
@Composable
private fun BookInfoText(section: String, content: String) {
    Row {
        Text(text = section)
        Spacer(modifier = Modifier.width(4.dp))
        Text(modifier = Modifier.weight(1f), text = content, overflow = TextOverflow.Ellipsis)
    }
}

/**
 * 미리보기 상세 화면
 */
@Preview
@Composable
private fun PreviewBookDetail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BookDetail(
            BookData(
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
            )
        )
    }
}