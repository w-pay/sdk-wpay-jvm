package au.com.woolworths.village.sdk.model

interface TransactionRefundDetails {
    val reason: String

    val clientReference: String?
}