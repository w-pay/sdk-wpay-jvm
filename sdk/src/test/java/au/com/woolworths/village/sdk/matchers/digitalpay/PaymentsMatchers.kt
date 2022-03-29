package au.com.woolworths.village.sdk.matchers.digitalpay

import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.model.digitalpay.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.*

fun digitalPayPaymentResponseFrom(dto: JsonObject): Matcher<DigitalPayPaymentResponse> =
    object : Matcher<DigitalPayPaymentResponse> {
        override fun test(value: DigitalPayPaymentResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["partialSuccess"]?.toBoolean(), value.partialSuccess),
                MatcherResult.test(::digitalPayFraudResponseFrom, dto["fraudResponse"], value.fraudResponse),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayCreditCard>(::digitalPayCreditCardFrom),
                    dto["creditCards"]?.jsonArray,
                    value.creditCards
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayGiftCard>(::digitalPayGiftCardFrom),
                    dto["giftCards"]?.jsonArray,
                    value.giftCards
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayPayPal>(::digitalPayPayPalFrom),
                    dto["payPal"]?.jsonArray,
                    value.payPal
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayGooglePay>(::digitalPayGooglePayFrom),
                    dto["googlePay"]?.jsonArray,
                    value.googlePay
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayApplePay>(::digitalPayApplePayFrom),
                    dto["applePay"]?.jsonArray,
                    value.applePay
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayPaymentInstrument>(::digitalPayPaymentInstrumentFrom),
                    dto["unknown"]?.jsonArray,
                    value.unknown
                )
            )
    }

fun digitalPayPaymentInstrumentTypeFrom(dto: JsonObject): Matcher<DigitalPayPaymentInstrumentType> =
    object : Matcher<DigitalPayPaymentInstrumentType> {
        override fun test(value: DigitalPayPaymentInstrumentType): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["errorCode"]?.content(), value.errorCode),
                MatcherResult.test(::equal, dto["errorMessage"]?.content(), value.errorMessage),
                MatcherResult.test(::equal, dto["errorDetail"]?.content(), value.errorDetail)
            )
    }

fun digitalPayCreditCardFrom(dto: JsonElement): Matcher<DigitalPayCreditCard> =
    digitalPayCreditCardFrom(dto.jsonObject)

fun digitalPayCreditCardFrom(dto: JsonObject): Matcher<DigitalPayCreditCard> =
    object : Matcher<DigitalPayCreditCard> {
        override fun test(value: DigitalPayCreditCard): MatcherResult =
            Matcher.tests(
                digitalPayPaymentInstrumentTypeFrom(dto).test(value),
                MatcherResult.test(::creditCardStepUpFrom, dto["stepUp"], value.stepUp),
                MatcherResult.test(::digitalPayCreditCardReceiptDataFrom, dto["receiptData"], value.receiptData),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayExtendedTransactionData>(::digitalPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                ),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage),
                MatcherResult.test(::digitalPayHandlingInstructionsFrom, dto["handlingInstructions"], value.handlingInstructions),
                MatcherResult.test(::digitalPayThreeDSResponseFrom, dto["threeDS"], value.threeDS),
            )
    }

fun digitalPayCreditCardReceiptDataFrom(dto: JsonElement): Matcher<DigitalPayCreditCardReceiptData> =
    digitalPayCreditCardReceiptDataFrom(dto.jsonObject)

fun digitalPayCreditCardReceiptDataFrom(dto: JsonObject): Matcher<DigitalPayCreditCardReceiptData> =
    object : Matcher<DigitalPayCreditCardReceiptData> {
        override fun test(value: DigitalPayCreditCardReceiptData): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["scheme"]?.content(), value.scheme),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear)
            )
    }

fun digitalPayHandlingInstructionsFrom(dto: JsonElement): Matcher<DigitalPayHandlingInstructions> =
    digitalPayHandlingInstructionsFrom(dto.jsonObject)

fun digitalPayHandlingInstructionsFrom(dto: JsonObject): Matcher<DigitalPayHandlingInstructions> =
    object : Matcher<DigitalPayHandlingInstructions> {
        override fun test(value: DigitalPayHandlingInstructions): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["instructionCode"]?.toUpperCase(), value.instructionCode.toString()),
                MatcherResult.test(::equal, dto["instructionMessage"]?.content(), value.instructionMessage)
            )
    }

fun digitalPayThreeDSResponseFrom(dto: JsonElement): Matcher<DigitalPayThreeDSResponse> =
    digitalPayThreeDSResponseFrom(dto.jsonObject)

fun digitalPayThreeDSResponseFrom(dto: JsonObject): Matcher<DigitalPayThreeDSResponse> =
    object : Matcher<DigitalPayThreeDSResponse> {
        override fun test(value: DigitalPayThreeDSResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["car"]?.content(), value.car),
                MatcherResult.test(::equal, dto["dsTransID"]?.content(), value.dsTransID),
                MatcherResult.test(::equal, dto["sli"]?.content(), value.sli)
            )
    }

fun digitalPayGiftCardFrom(dto: JsonElement): Matcher<DigitalPayGiftCard> =
    digitalPayGiftCardFrom(dto.jsonObject)

fun digitalPayGiftCardFrom(dto: JsonObject): Matcher<DigitalPayGiftCard> =
    object : Matcher<DigitalPayGiftCard> {
        override fun test(value: DigitalPayGiftCard): MatcherResult =
            Matcher.tests(
                digitalPayPaymentInstrumentTypeFrom(dto).test(value),
                MatcherResult.test(::creditCardStepUpFrom, dto["stepUp"], value.stepUp),
                MatcherResult.test(::digitalPayGiftCardReceiptDataFrom, dto["receiptData"], value.receiptData),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayGiftCardReceiptDataFrom(dto: JsonElement): Matcher<DigitalPayGiftCardReceiptData> =
    digitalPayGiftCardReceiptDataFrom(dto.jsonObject)

fun digitalPayGiftCardReceiptDataFrom(dto: JsonObject): Matcher<DigitalPayGiftCardReceiptData> =
    object : Matcher<DigitalPayGiftCardReceiptData> {
        override fun test(value: DigitalPayGiftCardReceiptData): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix)
            )
    }

fun digitalPayPayPalFrom(dto: JsonElement): Matcher<DigitalPayPayPal> =
    digitalPayPayPalFrom(dto.jsonObject)

fun digitalPayPayPalFrom(dto: JsonObject): Matcher<DigitalPayPayPal> =
    object : Matcher<DigitalPayPayPal> {
        override fun test(value: DigitalPayPayPal): MatcherResult =
            Matcher.tests(
                digitalPayPaymentInstrumentTypeFrom(dto).test(value),
                MatcherResult.test(::digitalPayPalReceiptDataFrom, dto["receiptData"], value.receiptData),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayPalReceiptDataFrom(dto: JsonElement): Matcher<DigitalPayPayPalReceiptData> =
    digitalPayPalReceiptDataFrom(dto.jsonObject)

fun digitalPayPalReceiptDataFrom(dto: JsonObject): Matcher<DigitalPayPayPalReceiptData> =
    object : Matcher<DigitalPayPayPalReceiptData> {
        override fun test(value: DigitalPayPayPalReceiptData): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["payPalId"]?.content(), value.payPalId),
                MatcherResult.test(::equal, dto["customerId"]?.content(), value.customerId)
            )
    }

fun digitalPayGooglePayFrom(dto: JsonElement): Matcher<DigitalPayGooglePay> =
    digitalPayGooglePayFrom(dto.jsonObject)

fun digitalPayGooglePayFrom(dto: JsonObject): Matcher<DigitalPayGooglePay> =
    object : Matcher<DigitalPayGooglePay> {
        override fun test(value: DigitalPayGooglePay): MatcherResult =
            Matcher.tests(
                digitalPayPaymentInstrumentTypeFrom(dto).test(value),
                MatcherResult.test(::creditCardStepUpFrom, dto["stepUp"], value.stepUp),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayExtendedTransactionData>(::digitalPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                ),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayApplePayFrom(dto: JsonElement): Matcher<DigitalPayApplePay> =
    digitalPayApplePayFrom(dto.jsonObject)

fun digitalPayApplePayFrom(dto: JsonObject): Matcher<DigitalPayApplePay> =
    object : Matcher<DigitalPayApplePay> {
        override fun test(value: DigitalPayApplePay): MatcherResult =
            Matcher.tests(
                digitalPayPaymentInstrumentTypeFrom(dto).test(value),
                MatcherResult.test(::creditCardStepUpFrom, dto["stepUp"], value.stepUp)
            )
    }

fun digitalPayPaymentInstrumentFrom(dto: JsonElement): Matcher<DigitalPayPaymentInstrument> =
    digitalPayPaymentInstrumentFrom(dto.jsonObject)

fun digitalPayPaymentInstrumentFrom(dto: JsonObject): Matcher<DigitalPayPaymentInstrument> =
    object : Matcher<DigitalPayPaymentInstrument> {
        override fun test(value: DigitalPayPaymentInstrument): MatcherResult =
            digitalPayPaymentInstrumentTypeFrom(dto).test(value)
    }

fun digitalPayCompletionResponseFrom(dto: JsonObject): Matcher<DigitalPayCompletionResponse> =
    object : Matcher<DigitalPayCompletionResponse> {
        override fun test(value: DigitalPayCompletionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["partialSuccess"]?.toBoolean(), value.partialSuccess),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayTransactionCompletionResponse>(::digitalPayTransactionCompletionResponseFrom),
                    dto["completionResponses"]?.jsonArray,
                    value.completionResponses
                )
            )
    }

fun digitalPayTransactionCompletionResponseFrom(dto: JsonElement): Matcher<DigitalPayTransactionCompletionResponse> =
    digitalPayTransactionCompletionResponseFrom(dto.jsonObject)

fun digitalPayTransactionCompletionResponseFrom(dto: JsonObject): Matcher<DigitalPayTransactionCompletionResponse> =
    object : Matcher<DigitalPayTransactionCompletionResponse> {
        override fun test(value: DigitalPayTransactionCompletionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["completionTransactionRef"]?.content(), value.completionTransactionRef),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["errorCode"]?.content(), value.errorCode),
                MatcherResult.test(::equal, dto["errorMessage"]?.content(), value.errorMessage),
                MatcherResult.test(::equal, dto["errorDetail"]?.content(), value.errorDetail),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayVoidResponseFrom(dto: JsonObject): Matcher<DigitalPayVoidResponse> =
    object : Matcher<DigitalPayVoidResponse> {
        override fun test(value: DigitalPayVoidResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["partialSuccess"]?.toBoolean(), value.partialSuccess),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayVoidTransactionResponse>(::digitalPayTransactionVoidResponseFrom),
                    dto["voidResponses"]?.jsonArray,
                    value.voidResponses
                )
            )
    }

fun digitalPayTransactionVoidResponseFrom(dto: JsonElement): Matcher<DigitalPayVoidTransactionResponse> =
    digitalPayTransactionVoidResponseFrom(dto.jsonObject)

fun digitalPayTransactionVoidResponseFrom(dto: JsonObject): Matcher<DigitalPayVoidTransactionResponse> =
    object : Matcher<DigitalPayVoidTransactionResponse> {
        override fun test(value: DigitalPayVoidTransactionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["voidTransactionRef"]?.content(), value.voidTransactionRef),
                MatcherResult.test(::equal, dto["errorCode"]?.content(), value.errorCode),
                MatcherResult.test(::equal, dto["errorMessage"]?.content(), value.errorMessage),
                MatcherResult.test(::equal, dto["errorDetail"]?.content(), value.errorDetail),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayRefundResponseFrom(dto: JsonObject): Matcher<DigitalPayRefundResponse> =
    object : Matcher<DigitalPayRefundResponse> {
        override fun test(value: DigitalPayRefundResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["partialSuccess"]?.toBoolean(), value.partialSuccess),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayRefundTransactionResponse>(::digitalPayTransactionRefundResponseFrom),
                    dto["refundResponses"]?.jsonArray,
                    value.refundResponses
                )
            )
    }

fun digitalPayTransactionRefundResponseFrom(dto: JsonElement): Matcher<DigitalPayRefundTransactionResponse> =
    digitalPayTransactionRefundResponseFrom(dto.jsonObject)

fun digitalPayTransactionRefundResponseFrom(dto: JsonObject): Matcher<DigitalPayRefundTransactionResponse> =
    object : Matcher<DigitalPayRefundTransactionResponse> {
        override fun test(value: DigitalPayRefundTransactionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["refundTransactionRef"]?.content(), value.refundTransactionRef),
                MatcherResult.test(::equal, dto["errorCode"]?.content(), value.errorCode),
                MatcherResult.test(::equal, dto["errorMessage"]?.content(), value.errorMessage),
                MatcherResult.test(::equal, dto["errorDetail"]?.content(), value.errorDetail),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }
