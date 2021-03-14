package au.com.woolworths.village.sdk

/**
 * Adds the Wallet ID to the request
 */
class WalletIdRequestHeader(val walletId: String) : RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_WALLET_ID] = this.walletId
    }
}