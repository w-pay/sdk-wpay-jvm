package au.com.woolworths.village.sdk.model

interface TransactionRefundDetails {
    fun reason(): String

    fun clientReference(): String?
}