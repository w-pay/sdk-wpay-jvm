package au.com.woolworths.village.sdk

class EverydayPayWalletHeader(
    private val options: VillageOptions
) : RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_EVERYDAY_PAY_WALLET] = (options.wallet == Wallet.EVERYDAY_PAY).toString()
    }
}