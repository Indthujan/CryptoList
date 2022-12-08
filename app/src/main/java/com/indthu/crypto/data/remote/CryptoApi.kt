package com.indthu.crypto.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinData>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailData
}