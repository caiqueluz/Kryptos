package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.data.dto.CurrenciesImagesDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingDTO
import com.caiqueluz.kryptos.data.dto.CurrenciesListingItemDTO
import com.caiqueluz.kryptos.data.dto.CurrencyImageItemDTO
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyItemConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrenciesConverterTest {

    private val mockQuoteConverter = mock<CurrencyQuoteConverter> {
        on { convertQuote(any()) } doReturn mock()
    }

    private val mockItemConverter = mock<CurrencyItemConverter> {
        on { convertCurrencyItem(any(), any(), any()) } doReturn mock()
    }

    private val converter = CurrenciesConverter(
        quoteConverter = mockQuoteConverter,
        itemConverter = mockItemConverter
    )

    private val fakeCurrenciesListingDTO = CurrenciesListingDTO(
        currencies = listOf(
            createFakeListingItemDTO(id = 1),
            createFakeListingItemDTO(id = 2),
            createFakeListingItemDTO(id = 3)
        )
    )

    private val fakeCurrenciesImagesDTO = CurrenciesImagesDTO(
        currenciesImages = mapOf(
            "1" to CurrencyImageItemDTO(id = 1, String()),
            "2" to CurrencyImageItemDTO(id = 2, String()),
            "3" to CurrencyImageItemDTO(id = 3, String())
        )
    )

    @Test
    fun whenConvertIdsIsCalled_verifyResponseIsCorrect() {
        val expected = "1,2,3"
        val actual = converter.convertIds(fakeCurrenciesListingDTO)

        assertEquals(expected, actual)
    }

    @Test
    fun whenConvertCurrenciesIsCalled_verifyResponseSizeIsCorrect() {
        val expected = 3
        val actual = converter.convertCurrencies(
            fakeCurrenciesListingDTO, fakeCurrenciesImagesDTO
        ).currencies.size

        assertEquals(expected, actual)
    }

    private fun createFakeListingItemDTO(id: Int) = CurrenciesListingItemDTO(
        id = id, name = String(), symbol = String(), quote = mock()
    )
}
