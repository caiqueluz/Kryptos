package com.caiqueluz.kryptos.ui.converter

import com.caiqueluz.kryptos.data.domain.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.domain.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.domain.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.domain.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.domain.CurrenciesVO
import com.caiqueluz.kryptos.ui.domain.CurrencyItemVO
import com.caiqueluz.kryptos.utils.ImageLoader
import javax.inject.Inject

class CurrenciesConverter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val quoteConverter: CurrencyQuoteConverter
) {

    fun convertIds(listing: CurrenciesListingDTO): String =
        listing.currencies.map { it.id.toString() }
            .joinToString { it }
            .filterNot { it.isWhitespace() }

    fun convertCurrencies(
        listing: CurrenciesListingDTO, images: CurrenciesImagesDTO
    ): CurrenciesVO = CurrenciesVO(
        currencies = images.currenciesImages.convertCurrencyItems(listing)
    )

    private fun Map<String, CurrencyImageItemDTO>.convertCurrencyItems(
        listing: CurrenciesListingDTO
    ): List<CurrencyItemVO> = this.map { (_, dto) ->
        val listingItem = listing.getListingItem(dto)
        val quoteDTO = listingItem.quote
        val quote = quoteConverter.convertQuote(quoteDTO)

        return@map CurrencyItemVO(
            name = listingItem.name,
            symbol = listingItem.symbol,
            image = imageLoader.loadImage(dto.imageUrl),
            quote = quote
        )
    }

    private fun CurrenciesListingDTO.getListingItem(
        imageItem: CurrencyImageItemDTO
    ): CurrenciesListingItemDTO = this.currencies.first { listingItem ->
        listingItem.id == imageItem.id
    }
}
