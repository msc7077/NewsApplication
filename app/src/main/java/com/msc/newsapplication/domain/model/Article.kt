package com.msc.newsapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * @Entity 를 통해 테이블을 만든다.
 *
 */
@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source, // update, delete 등의 기능을 하려면 기본 데이터 객체 (String, Int 등) 로 변환이 필요하다.
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
)