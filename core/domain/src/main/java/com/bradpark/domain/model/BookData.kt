package com.bradpark.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BookData(
    val title: String,              //도서 제목
    val contents: String,           //도서 소개
    val url: String,                //도서 상세 URL
    val isbn: String,               //국제 표준 도서번호, ISBN10 또는 ISBN13
    val datetime: String,           //도서 출판날짜, ISO 8601 형식
    val authors: List<String>,      //도서 저자 리스트
    val publisher: String,          //도서 출판사
    val translators: List<String>,  //도서 번역자 리스트
    val price: Int,                 //도서 정가
    val salePrice: Int,            //도서 판매가
    val thumbnail: String,          //도서 표지 썸네일 URL
    val status: String              //도서 판매 상태 정보(정상, 품절, 절판 등
)
