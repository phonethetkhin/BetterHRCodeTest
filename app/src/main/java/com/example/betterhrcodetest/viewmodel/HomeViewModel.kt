package com.example.betterhrcodetest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.betterhrcodetest.datasource.LaunchListPagingSource
import com.example.betterhrcodetest.repo.HomeRepo
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    suspend fun getDetail(missionId: String) = homeRepo.getDetail(missionId)

    val launchListLiveData =
        Pager(PagingConfig(pageSize = 10, enablePlaceholders = false)) {
            LaunchListPagingSource(homeRepo)
        }.flow
}