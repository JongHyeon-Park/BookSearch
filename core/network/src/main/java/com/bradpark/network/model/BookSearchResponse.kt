package com.bradpark.network.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class BookSearchResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("documents")
    val documents: List<Document>
)

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int,       //검색된 문서 수
    @SerializedName("pageable_count")
    val pageableCount: Int,    //total_count 중에 노출 가능 문서 수, 최대 1,000
    @SerializedName("is_end")
    val isEnd: Boolean         //현재 페이지가 마지막 페이지인지 여부
)

data class Document(
    @SerializedName("title")
    val title: String,              //도서 제목
    @SerializedName("contents")
    val contents: String,           //도서 소개
    @SerializedName("url")
    val url: String,                //도서 상세 URL
    @SerializedName("isbn")
    val isbn: String,               //국제 표준 도서번호, ISBN10 또는 ISBN13
    @SerializedName("datetime")
    val datetime: Date,             //도서 출판날짜, ISO 8601 형식
    @SerializedName("authors")
    val authors: List<String>,      //도서 저자 리스트
    @SerializedName("publisher")
    val publisher: String,          //도서 출판사
    @SerializedName("translators")
    val translators: List<String>,  //도서 번역자 리스트
    @SerializedName("price")
    val price: Int,                 //도서 정가
    @SerializedName("sale_price")
    val salePrice: Int,             //도서 판매가
    @SerializedName("thumbnail")
    val thumbnail: String,          //도서 표지 썸네일 URL
    @SerializedName("status")
    val status: String              //도서 판매 상태 정보(정상, 품절, 절판 등
)
