package com.msc.application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msc.application.domain.model.Article
import kotlinx.coroutines.flow.Flow


/***
 * @Dao 를 통해 Room 데이터 베이스로 되어있는 local 데이터들을 RUD 기능을 하는 인터페이스를
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 데이터 충돌에 대한 처리 옵션으로 -> 이미 있는 기사를 업데이트 하려하면 최신으로 업데이트 하겠다는 의미이다.
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles() : Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String) : Article?

}