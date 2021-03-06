package com.caiqueluz.kryptos.data

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import retrofit2.Response

class CurrenciesRepository(
    private val api: CurrenciesApi
) {

    suspend fun fetchCurrenciesListing(limit: Int): Response<CurrenciesListingDTO> =
        api.fetchCurrenciesListing(limit)

    suspend fun fetchCurrenciesWithImages(ids: String): Response<CurrenciesImagesDTO> =
        api.fetchCurrenciesWithImages(ids)
}
