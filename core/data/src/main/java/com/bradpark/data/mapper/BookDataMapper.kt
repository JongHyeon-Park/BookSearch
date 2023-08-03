package com.bradpark.data.mapper

import com.bradpark.domain.model.BookData
import com.bradpark.network.model.Document
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * 네트워크 데이터를 도메인 데이터로 변환
 */
fun Document.asData(): BookData {
    return BookData(
        authors = authors,
        contents = contents,
        datetime = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(datetime),
        isbn = isbn,
        price = price,
        publisher = publisher,
        salePrice = salePrice,
        thumbnail = thumbnail,
        title = title,
        translators = translators,
        url = url,
        status = status
    )
}