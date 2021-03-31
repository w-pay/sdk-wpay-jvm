package au.com.woolworths.village.sdk

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class EverydayPayWalletHeaderTest {
    @Test
    fun itShouldSetEverydayPayWalletHeaderToTrueWhenEverydayPayWalletChosen() {
        val options = VillageOptions("", "", Wallet.EVERYDAY_PAY)

        val headers = createHeaders(options)

        assertThat(headers[X_EVERYDAY_PAY_WALLET], equalTo("true"))
    }

    @Test
    fun itShouldSetEverydayPayWalletHeaderToFalseWhenEverydayPayWalletNotChosen() {
        val options = VillageOptions("", "", Wallet.MERCHANT)

        val headers = createHeaders(options)

        assertThat(headers[X_EVERYDAY_PAY_WALLET], equalTo("false"))
    }

    @Test
    fun itShouldSetEverydayPayWalletHeaderToFalseWhenNoWalletChosen() {
        val options = VillageOptions("", "", null)

        val headers = createHeaders(options)

        assertThat(headers[X_EVERYDAY_PAY_WALLET], equalTo("false"))
    }

    private fun createHeaders(options: VillageOptions): MutableMap<String, String> {
        val factory = EverydayPayWalletHeader(options)

        val headers: MutableMap<String, String> = mutableMapOf()
        factory.addHeaders(headers)

        return headers
    }
}