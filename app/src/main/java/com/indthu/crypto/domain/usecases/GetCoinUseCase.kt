package com.indthu.crypto.domain.usecases

import com.indthu.crypto.common.Resource
import com.indthu.crypto.data.remote.dto.toCoin
import com.indthu.crypto.data.remote.dto.toCoinDetail
import com.indthu.crypto.domain.model.Coin
import com.indthu.crypto.domain.model.CoinDetail
import com.indthu.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(id).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your Internet!"))
        }
    }
}