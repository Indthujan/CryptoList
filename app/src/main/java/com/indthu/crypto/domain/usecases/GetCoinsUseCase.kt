package com.indthu.crypto.domain.usecases

import com.indthu.crypto.common.Resource
import com.indthu.crypto.data.remote.dto.toCoin
import com.indthu.crypto.domain.model.Coin
import com.indthu.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your Internet!"))
        }
    }
}