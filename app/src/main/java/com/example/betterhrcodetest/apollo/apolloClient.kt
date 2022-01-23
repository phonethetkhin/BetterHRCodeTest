package com.example.betterhrcodetest.apollo

import com.apollographql.apollo3.ApolloClient
import com.example.betterhrcodetest.util.BASE_URL

val apolloClient = ApolloClient.Builder()
    .serverUrl(BASE_URL)
    .build()