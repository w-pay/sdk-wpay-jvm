package au.com.woolworths.village.sdk

class WalletIdRequestHeader: RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_WALLET_ID] = "10006"
    }
}