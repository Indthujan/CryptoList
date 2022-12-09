package com.indthu.crypto.domain.repository

import com.indthu.crypto.data.remote.dto.CoinData
import com.indthu.crypto.data.remote.dto.CoinDetailData

interface CoinRepository {

    suspend fun getCoins(): List<CoinData>

    suspend fun getCoinById(id: String): CoinDetailData
}