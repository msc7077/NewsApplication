package com.msc.application.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/***
 * @Entity 를 통해 테이블을 만든다.
 *
 */
@Parcelize // 데이터들의 직렬화 를 통해 데이터 전달시 data class 그대로 전달이 가능해진다. - Serializable 의 업그레이드
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source, // update, delete 등의 기능을 하려면 기본 데이터 객체 (String, Int 등) 로 변환이 필요하다.
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
): Parcelable