package com.indthu.crypto.di

import com.indthu.crypto.common.Constants
import com.indthu.crypto.data.remote.dto.CryptoApi
import com.indthu.crypto.data.repository.CoinRepositoryImpl
import com.indthu.crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CryptoApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }
}