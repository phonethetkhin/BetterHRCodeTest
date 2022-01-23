package com.example.betterhrcodetest.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.betterhrcodetest.LaunchListQuery
import com.example.betterhrcodetest.repo.HomeRepo
import com.example.betterhrcodetest.util.LOAD_SIZE
import com.example.betterhrcodetest.util.START_INDEX
import javax.inject.Inject


class LaunchListPagingSource @Inject constructor(
    private val repository: HomeRepo,
) : PagingSource<Int, LaunchListQuery.Launch>() {

    override fun getRefreshKey(state: PagingState<Int, LaunchListQuery.Launch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchListQuery.Launch> {
        val list = ArrayList<LaunchListQuery.Launch>()
        return try {
            val offset = (params.key ?: START_INDEX)
            val limit = LOAD_SIZE
            val response = repository.getList(offset, limit)
            val responseBody = response!!.data?.launches?.sortedBy { it!!.mission_name }
            Log.d("boom", "$offset $limit $responseBody")
            val nextKey =
                if (responseBody.isNullOrEmpty()) {
                    null
                } else {
                    offset + LOAD_SIZE
                }
            responseBody!!.forEach {
                list.add(it!!)
            }
            LoadResult.Page(data = list, null, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}
