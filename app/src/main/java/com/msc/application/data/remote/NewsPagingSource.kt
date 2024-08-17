package com.msc.application.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msc.application.domain.model.Article

/***
 * Paging3 를 사용하여 API 에서 응답 받아 로드할 데이터 수를 지정할 수 있다. 이로인해 빠르게 로드가 가능해진다.
 *
 */
class NewsPagingSource(
    private val newsApi: NewsApi, // 원하는 뉴스를 가져올 수 있도록 함.
    private val sources: String   //
): PagingSource<Int, Article>() { // Int : 페이지, Article : 데이터

    private var totalNewsCount = 0

    /***
     * load
     * params 는 부모 개체가 있는 페이지를 가져온다.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } // 중복 제거
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}