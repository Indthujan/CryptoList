package com.indthu.crypto.data.repository

import com.indthu.crypto.data.remote.dto.CoinData
import com.indthu.crypto.data.remote.dto.CoinDetailData
import com.indthu.crypto.data.remote.dto.CryptoApi
import com.indthu.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CryptoApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinData> {
        return api.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetailData {
        return api.getCoinById(id)
    }
}