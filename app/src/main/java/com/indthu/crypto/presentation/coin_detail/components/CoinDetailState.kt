package com.indthu.crypto.presentation.coin_detail.components

import com.indthu.crypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""
)
