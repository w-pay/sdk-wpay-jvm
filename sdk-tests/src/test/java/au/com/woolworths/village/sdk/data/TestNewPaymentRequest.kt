package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.Basket
import au.com.woolworths.village.sdk.model.MerchantPayload
import au.com.woolworths.village.sdk.model.NewPaymentRequest
import au.com.woolworths.village.sdk.model.PosPayload
import java.math.BigDecimal

fun aNewPaymentRequest(): NewPaymentRequest {
    return TestNewPaymentRequest()
}

class TestNewPaymentRequest: NewPaymentRequest {
    override fun merchantReferenceId(): String {
        return "123456"
    }

    override fun grossAmount(): BigDecimal {
        return BigDecimal(12000)
    }

    override fun generateQR(): Boolean {
        return true
    }

    override fun maxUses(): Int? {
        return 1
    }

    override fun timeToLivePayment(): Int? {
        return 300
    }

    override fun timeToLiveQR(): Int? {
        return 60
    }

    override fun specificWalletId(): String? {
        return ""
    }

    override fun basket(): Basket? {
        return aNewBasket()
    }

    override fun posPayload(): PosPayload? {
        return aNewPosPayload()
    }

    override fun merchantPayload(): MerchantPayload? {
        return aNewMerchantPayload()
    }
}