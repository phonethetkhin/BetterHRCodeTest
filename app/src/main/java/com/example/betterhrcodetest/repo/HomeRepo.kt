package com.example.betterhrcodetest.repo

import android.content.Context
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.betterhrcodetest.LaunchDetailQuery
import com.example.betterhrcodetest.LaunchListQuery
import com.example.betterhrcodetest.apollo.apolloClient
import com.example.betterhrcodetest.util.showToast
import javax.inject.Inject

class HomeRepo @Inject constructor(private val context: Context) {

    suspend fun getList(offset: Int, limit: Int): ApolloResponse<LaunchListQuery.Data>? {
        var response: ApolloResponse<LaunchListQuery.Data>? = null
        try {
            response = apolloClient.query(
                LaunchListQuery(
                    Optional.Present(offset),
                    Optional.Present((limit))
                )
            ).execute()
        } catch (e: ApolloException) {
            context.showToast("Something went wrong ${e.message}")
        }
        return response
    }

    suspend fun getDetail(missionId: String): ApolloResponse<LaunchDetailQuery.Data>? {
        var response: ApolloResponse<LaunchDetailQuery.Data>? = null
        try {
            response = apolloClient.query(LaunchDetailQuery(missionId)).execute()
        } catch (e: ApolloException) {
            context.showToast("Oh no... A protocol error happened")
        }
        return response
    }

}