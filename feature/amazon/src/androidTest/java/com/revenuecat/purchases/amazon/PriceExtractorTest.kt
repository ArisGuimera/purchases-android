package com.revenuecat.purchases.amazon

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.text.Typography.nbsp

@RunWith(AndroidJUnit4::class)
class PriceExtractorTest {

    @Test
    fun `US_marketplace_$7_dot_12`() {
        val (currencyCode, priceAmountMicros) = "$7.12".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_120_000)
    }

    @Test
    fun `US_marketplace_$7_comma_12`() {
        val (currencyCode, priceAmountMicros) = "$7,12".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_120_000)
    }

    @Test
    fun `US_marketplace_$7_dot_1`() {
        val (currencyCode, priceAmountMicros) = "$7.1".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_100_000)
    }

    @Test
    fun `US_marketplace_$7_comma_1`() {
        val (currencyCode, priceAmountMicros) = "$7,1".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_100_000)
    }

    @Test
    fun `US_marketplace_$7_dot_123`() {
        val (currencyCode, priceAmountMicros) = "$7.123".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_123_000_000)
    }

    @Test
    fun `US_marketplace_$7_comma_123`() {
        val (currencyCode, priceAmountMicros) = "$7,123".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_123_000_000)
    }

    @Test
    fun `US_marketplace_$7_123`() {
        val (currencyCode, priceAmountMicros) = "$7 123".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_123_000_000)
    }

    @Test
    fun `US_marketplace_$0_dot_99`() {
        val (currencyCode, priceAmountMicros) = "$0.99".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(990_000)
    }

    @Test
    fun `US_marketplace_$0_comma_99`() {
        val (currencyCode, priceAmountMicros) = "$0,99".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(990_000)
    }

    @Test
    fun `US_marketplace_$1`() {
        val (currencyCode, priceAmountMicros) = "$1".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000)
    }

    @Test
    fun `US_marketplace_$10M`() {
        val (currencyCode, priceAmountMicros) = "$10000000".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(10_000_000_000_000)
    }

    @Test
    fun `US_marketplace_$1M_with_decimal_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "$1,000,000.00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000_000_000)
    }

    @Test
    fun `US_marketplace_$1M_with_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "$1.000.000,00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000_000_000)
    }

    @Test
    fun `US_marketplace_$0_dot_1234`() {
        val (currencyCode, priceAmountMicros) = "$0.1234".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(123_400)
    }

    @Test
    fun `US_marketplace_$0_comma_1234`() {
        val (currencyCode, priceAmountMicros) = "$0,1234".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(123_400)
    }

    @Test
    fun `US_marketplace_$1_comma_0000000`() {
        val (currencyCode, priceAmountMicros) = "$1,0000000".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000)
    }

    @Test
    fun `US_marketplace_$1000_dot`() {
        val (currencyCode, priceAmountMicros) = "$1,000.00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000_000)
    }

    @Test
    fun `US_marketplace_$100000_dot_no_decimals`() {
        val (currencyCode, priceAmountMicros) = "$100.000".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(100_000_000_000)
    }

    @Test
    fun `US_marketplace_$100000_comma_no_decimals`() {
        val (currencyCode, priceAmountMicros) = "$100,000".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(100_000_000_000)
    }

    @Test
    fun `US_marketplace_$1000_dot_without_comma`() {
        val (currencyCode, priceAmountMicros) = "$1000.00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000_000)
    }

    @Test
    fun `US_marketplace_$1000_comma_without_dot`() {
        val (currencyCode, priceAmountMicros) = "$1000,00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(1_000_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$`() {
        val (currencyCode, priceAmountMicros) = "US$7.00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_space`() {
        val (currencyCode, priceAmountMicros) = "US$ 7.00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_comma`() {
        val (currencyCode, priceAmountMicros) = "US$7,00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_comma_space`() {
        val (currencyCode, priceAmountMicros) = "US$ 7,00".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_symbol_after`() {
        val (currencyCode, priceAmountMicros) = "7.00$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_comma_symbol_after`() {
        val (currencyCode, priceAmountMicros) = "7,00$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_symbol_after`() {
        val (currencyCode, priceAmountMicros) = "7.00US$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_space_symbol_after`() {
        val (currencyCode, priceAmountMicros) = "7.00 US$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_comma_space_symbol_after`() {
        val (currencyCode, priceAmountMicros) = "7,00 US$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `Inconsistent_case_with_IN_marketplace_but_price_in_dollars_sets_symbol_as_currency_code`() {
        val (currencyCode, priceAmountMicros) = "US$ 7.00".extractPrice("IN")
        assertThat(currencyCode).isEqualTo("INR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7_dot`() {
        val (currencyCode, priceAmountMicros) = "CA$7.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7_comma`() {
        val (currencyCode, priceAmountMicros) = "CA$7,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7000_dot`() {
        val (currencyCode, priceAmountMicros) = "CA$7000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7000_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "CA$7,000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7000_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "CA$7000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$7000_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "CA$7.000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7CA$_dot`() {
        val (currencyCode, priceAmountMicros) = "7.00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7CA$_comma`() {
        val (currencyCode, priceAmountMicros) = "7,00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7000CA$_dot`() {
        val (currencyCode, priceAmountMicros) = "7000.00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000CA$_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "7,000.00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000CA$_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "7000,00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000CA$_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "7.000,00CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7_dot`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7_comma`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7000_dot`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7000_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7,000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7000_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_CA$_7000_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "CA$ 7.000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7_CA$_dot`() {
        val (currencyCode, priceAmountMicros) = "7.00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7_CA$_comma`() {
        val (currencyCode, priceAmountMicros) = "7,00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7000_CA$_dot`() {
        val (currencyCode, priceAmountMicros) = "7000.00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000_CA$_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "7,000.00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000_CA$_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "7000,00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000_CA$_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "7.000,00 CA$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7_dot`() {
        val (currencyCode, priceAmountMicros) = "$7.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_$7_comma`() {
        val (currencyCode, priceAmountMicros) = "$7,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_dot`() {
        val (currencyCode, priceAmountMicros) = "$7000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "$7,000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "$7000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "$7.000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7$_dot`() {
        val (currencyCode, priceAmountMicros) = "7.00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7$_comma`() {
        val (currencyCode, priceAmountMicros) = "7,00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_dot`() {
        val (currencyCode, priceAmountMicros) = "7000.00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_dot_and_commas`() {
        val (currencyCode, priceAmountMicros) = "7,000.00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_decimal_comma`() {
        val (currencyCode, priceAmountMicros) = "7000,00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_decimal_comma_and_dots`() {
        val (currencyCode, priceAmountMicros) = "7.000,00$".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7_dot_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_$7_comma_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_dot_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_dot_and_commas_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7,000.00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_decimal_comma_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_$7000_decimal_comma_and_dots_space`() {
        val (currencyCode, priceAmountMicros) = "$ 7.000,00".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7$_dot_space`() {
        val (currencyCode, priceAmountMicros) = "7.00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7$_comma_space`() {
        val (currencyCode, priceAmountMicros) = "7,00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_dot_space`() {
        val (currencyCode, priceAmountMicros) = "7000.00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_dot_and_commas_space`() {
        val (currencyCode, priceAmountMicros) = "7,000.00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_decimal_comma_space`() {
        val (currencyCode, priceAmountMicros) = "7000,00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `CA_marketplace_7000$_decimal_comma_and_dots_space`() {
        val (currencyCode, priceAmountMicros) = "7.000,00 $".extractPrice("CA")
        assertThat(currencyCode).isEqualTo("CAD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000_000)
    }

    @Test
    fun `BR_marketplace_7_BRL_in_US`() {
        val (currencyCode, priceAmountMicros) = "R$7.00".extractPrice("BR")
        assertThat(currencyCode).isEqualTo("BRL")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `BR_marketplace_7_BRL_in_Brazil`() {
        val (currencyCode, priceAmountMicros) = "R$7,00".extractPrice("BR")
        assertThat(currencyCode).isEqualTo("BRL")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `BR_marketplace_7_BRL_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "BRL7,00".extractPrice("BR")
        assertThat(currencyCode).isEqualTo("BRL")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `MX_marketplace_7_MXN_in_US`() {
        val (currencyCode, priceAmountMicros) = "MX$7.00".extractPrice("MX")
        assertThat(currencyCode).isEqualTo("MXN")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `MX_marketplace_7_MXN_in_Mexico`() {
        val (currencyCode, priceAmountMicros) = "MX$7,00".extractPrice("MX")
        assertThat(currencyCode).isEqualTo("MXN")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `MX_marketplace_7_MXN_in_Brazil`() {
        val (currencyCode, priceAmountMicros) = "MX$7,00".extractPrice("MX")
        assertThat(currencyCode).isEqualTo("MXN")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `MX_marketplace_7_MXN_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "MXN7,00".extractPrice("MX")
        assertThat(currencyCode).isEqualTo("MXN")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `GB_marketplace_£7_comma`() {
        val (currencyCode, priceAmountMicros) = "£7.00".extractPrice("GB")
        assertThat(currencyCode).isEqualTo("GBP")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `GB_marketplace_GBP7_comma`() {
        val (currencyCode, priceAmountMicros) = "GBP7,00".extractPrice("GB")
        assertThat(currencyCode).isEqualTo("GBP")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `DE_marketplace_7_Euro_in_US`() {
        val (currencyCode, priceAmountMicros) = "€7.00".extractPrice("DE")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `DE_marketplace_7_Euro_in_Germany`() {
        val (currencyCode, priceAmountMicros) = "7,00 €".extractPrice("DE")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `DE_marketplace_7_Euro_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "7,00 €".extractPrice("DE")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `ES_marketplace_7_Euro_in_US`() {
        val (currencyCode, priceAmountMicros) = "€7.00".extractPrice("ES")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `FR_marketplace_7_Euro_in_US`() {
        val (currencyCode, priceAmountMicros) = "€7.00".extractPrice("FR")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `IT_marketplace_7_Euro_in_US`() {
        val (currencyCode, priceAmountMicros) = "€7.00".extractPrice("IT")
        assertThat(currencyCode).isEqualTo("EUR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `IN_marketplace_7_INR_in_US`() {
        val (currencyCode, priceAmountMicros) = "₹7.00".extractPrice("IN")
        assertThat(currencyCode).isEqualTo("INR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `IN_marketplace_7_INR_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "INR7,00".extractPrice("IN")
        assertThat(currencyCode).isEqualTo("INR")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `JP_marketplace_7_JPY_in_US`() {
        val (currencyCode, priceAmountMicros) = "¥7.00".extractPrice("JP")
        assertThat(currencyCode).isEqualTo("JPY")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `JP_marketplace_7_JPY_in_Japan`() {
        val (currencyCode, priceAmountMicros) = "¥7.00".extractPrice("JP")
        assertThat(currencyCode).isEqualTo("JPY")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `JP_marketplace_7_JPY_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "¥7.00".extractPrice("JP")
        assertThat(currencyCode).isEqualTo("JPY")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `AU_marketplace_7_AUD_in_US`() {
        val (currencyCode, priceAmountMicros) = "A$7.00".extractPrice("AU")
        assertThat(currencyCode).isEqualTo("AUD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `AU_marketplace_7_AUD_in_Spain`() {
        val (currencyCode, priceAmountMicros) = "AUD7,00".extractPrice("AU")
        assertThat(currencyCode).isEqualTo("AUD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
    }

    @Test
    fun `US_marketplace_7_USD_US$_space_symbol_after_and_nbsp`() {
        val (currencyCode, priceAmountMicros) = "7.00${nbsp}US$".extractPrice("US")
        assertThat(currencyCode).isEqualTo("USD")
        assertThat(priceAmountMicros).isEqualTo(7_000_000)
        val anotherPriceWithNBSP = "5,80 €"
        val (anotherCurrencyCode, anotherPriceAmountMicros) = anotherPriceWithNBSP.extractPrice("DE")
        assertThat(anotherCurrencyCode).isEqualTo("EUR")
        assertThat(anotherPriceAmountMicros).isEqualTo(5_800_000)
    }
}
