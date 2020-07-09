package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.TransactionRefundDetails

fun aNewTransactionRefund(): TransactionRefundDetails {
    return TestTransactionRefundDetails()
}

class TestTransactionRefundDetails: TransactionRefundDetails {
    override fun reason(): String {
        return ""
    }
}