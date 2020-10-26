package au.com.woolworths.village.sdk

class MerchantIdRequestHeader(
    private val merchantId: String
) : RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_MERCHANT_ID] = merchantId
    }
}