package au.com.wpay.sdk.matchers.walletmanagement

import au.com.wpay.sdk.matchers.*
import au.com.wpay.sdk.model.walletmanagement.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun importPaymentInstrumentsResponseFrom(dto: JsonObject): Matcher<ImportPaymentInstrumentsResponse> =
    object : Matcher<ImportPaymentInstrumentsResponse> {
        override fun test(value: ImportPaymentInstrumentsResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["uid"]?.content(), value.uid),
                MatcherResult.test(::equal, dto["shopperId"]?.content(), value.shopperId),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, CreditCardResult>(::creditCardResultFrom),
                    dto["creditCards"]?.jsonArray,
                    value.creditCards
                ),
                MatcherResult.test(::payPalFrom, dto["payPal"], value.payPal)
            )
    }

fun creditCardResultFrom(dto: JsonElement): Matcher<CreditCardResult> =
    creditCardResultFrom(dto.jsonObject)

fun creditCardResultFrom(dto: JsonObject): Matcher<CreditCardResult> =
    object : Matcher<CreditCardResult> {
        override fun test(value: CreditCardResult): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionRef"]?.content(), value.transactionRef),
                MatcherResult.test(::equal, dto["transactionTimestamp"]?.content(), value.transactionTimestamp),
                MatcherResult.test(::equal, dto["transactionType"]?.content(), value.transactionType),
                MatcherResult.test(::equal, dto["transactionResponseCode"]?.content(), value.transactionResponseCode),
                MatcherResult.test(::equal, dto["transactionResponseText"]?.content(), value.transactionResponseText),
                MatcherResult.test(::equal, dto["orderNumber"]?.content(), value.orderNumber),
                MatcherResult.test(::equal, dto["bin"]?.content(), value.bin),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["result"]?.toUpperCase(), value.result.toString()),
                MatcherResult.test(::errorMessageFrom, dto["errorMessage"], value.errorMessage),
            )
    }

fun errorMessageFrom(dto: JsonElement): Matcher<ErrorMessage> =
    errorMessageFrom(dto.jsonObject)

fun errorMessageFrom(dto: JsonObject): Matcher<ErrorMessage> =
    object : Matcher<ErrorMessage> {
        override fun test(value: ErrorMessage): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["description"]?.content(), value.description),
            )
    }

fun payPalFrom(dto: JsonElement): Matcher<PayPal> =
    payPalFrom(dto.jsonObject)

fun payPalFrom(dto: JsonObject): Matcher<PayPal> =
    object : Matcher<PayPal> {
        override fun test(value: PayPal): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["customerId"]?.content(), value.customerId),
                MatcherResult.test(::equal, dto["payPalId"]?.content(), value.payPalId),
                MatcherResult.test(::equal, dto["paymentMethodToken"]?.content(), value.paymentMethodToken),
                MatcherResult.test(::equal, dto["result"]?.toUpperCase(), value.result.toString()),
                MatcherResult.test(::errorMessageFrom, dto["errorMessage"], value.errorMessage),
            )
    }

fun verifyPaymentInstrumentsResponseFrom(dto: JsonObject): Matcher<VerifyPaymentInstrumentsSuccessResponse> =
    object : Matcher<VerifyPaymentInstrumentsSuccessResponse> {
        override fun test(value: VerifyPaymentInstrumentsSuccessResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["partialSuccess"]?.toBoolean(), value.partialSuccess),
                MatcherResult.test(::fraudResponseFrom, dto["fraudResponse"], value.fraudResponse),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, VerifyResponse>(::verifyResponseFrom),
                    dto["verifyResponses"]?.jsonArray,
                    value.verifyResponses
                ),
            )
    }

fun fraudResponseFrom(dto: JsonElement): Matcher<FraudResponse> =
    fraudResponseFrom(dto.jsonObject)

fun fraudResponseFrom(dto: JsonObject): Matcher<FraudResponse> =
    object : Matcher<FraudResponse> {
        override fun test(value: FraudResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["clientId"]?.content(), value.clientId),
                MatcherResult.test(::equal, dto["reasonCode"]?.content(), value.reasonCode),
                MatcherResult.test(::equal, dto["decision"]?.content(), value.decision)
            )
    }

fun verifyResponseFrom(dto: JsonElement): Matcher<VerifyResponse> =
    verifyResponseFrom(dto.jsonObject)

fun verifyResponseFrom(dto: JsonObject): Matcher<VerifyResponse> =
    object : Matcher<VerifyResponse> {
        override fun test(value: VerifyResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["verifyTransactionRef"]?.content(), value.verifyTransactionRef),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun listPaymentInstrumentsResponseFrom(dto: JsonObject): Matcher<ListPaymentInstrumentsResponse> =
    object : Matcher<ListPaymentInstrumentsResponse> {
        override fun test(value: ListPaymentInstrumentsResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, CreditCardDetails>(::creditCardDetailsFrom),
                    dto["creditCards"]?.jsonArray,
                    value.creditCards
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, GiftCardDetails>(::giftCardDetailsFrom),
                    dto["giftCards"]?.jsonArray,
                    value.giftCards
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, PayPalDetails>(::payPalDetailsFrom),
                    dto["payPal"]?.jsonArray,
                    value.payPal
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, PaymentAgreementDetails>(::paymentAgreementDetailsFrom),
                    dto["paymentAgreements"]?.jsonArray,
                    value.paymentAgreements
                ),
                MatcherResult.test(::googlePayDetailsFrom, dto["googlePay"], value.googlePay),
                MatcherResult.test(::applePayDetailsFrom, dto["applePay"], value.applePay)
            )
    }

fun creditCardDetailsFrom(dto: JsonElement): Matcher<CreditCardDetails> =
    creditCardDetailsFrom(dto.jsonObject)

fun creditCardDetailsFrom(dto: JsonObject): Matcher<CreditCardDetails> =
    object : Matcher<CreditCardDetails> {
        override fun test(value: CreditCardDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["scheme"]?.content(), value.scheme),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["cvvValidated"]?.toBoolean(), value.cvvValidated),
                MatcherResult.test(::equal, dto["cardName"]?.content(), value.cardName),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired),
                MatcherResult.test(::equal, dto["requiresCVV"]?.toBoolean(), value.requiresCVV),
                MatcherResult.test(::equal, dto["updateURL"]?.content(), value.updateURL),
                MatcherResult.test(::stepUpFrom, dto["stepUp"], value.stepUp),
            )
    }

fun stepUpFrom(dto: JsonElement): Matcher<StepUp> =
    stepUpFrom(dto.jsonObject)

fun stepUpFrom(dto: JsonObject): Matcher<StepUp> =
    object : Matcher<StepUp> {
        override fun test(value: StepUp): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["type"]?.toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["mandatory"]?.toBoolean(), value.mandatory),
                MatcherResult.test(::equal, dto["url"]?.content(), value.url)
            )
    }

fun giftCardDetailsFrom(dto: JsonElement): Matcher<GiftCardDetails> =
    giftCardDetailsFrom(dto.jsonObject)

fun giftCardDetailsFrom(dto: JsonObject): Matcher<GiftCardDetails> =
    object : Matcher<GiftCardDetails> {
        override fun test(value: GiftCardDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["programName"]?.content(), value.programName),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix)
            )
    }

fun payPalDetailsFrom(dto: JsonElement): Matcher<PayPalDetails> =
    payPalDetailsFrom(dto.jsonObject)

fun payPalDetailsFrom(dto: JsonObject): Matcher<PayPalDetails> =
    object : Matcher<PayPalDetails> {
        override fun test(value: PayPalDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["payPalId"]?.content(), value.payPalId),
                MatcherResult.test(::equal, dto["customerId"]?.content(), value.customerId)
            )
    }

fun paymentAgreementDetailsFrom(dto: JsonElement): Matcher<PaymentAgreementDetails> =
    paymentAgreementDetailsFrom(dto.jsonObject)

fun paymentAgreementDetailsFrom(dto: JsonObject): Matcher<PaymentAgreementDetails> =
    object : Matcher<PaymentAgreementDetails> {
        override fun test(value: PaymentAgreementDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["type"]?.toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["scheme"]?.content(), value.scheme),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["startDate"]?.content(), value.startDate),
                MatcherResult.test(::equal, dto["endDate"]?.content(), value.endDate),
                MatcherResult.test(::equal, dto["chargeFrequency"]?.toUpperCase(), value.chargeFrequency.toString()),
                MatcherResult.test(::equal, dto["chargeAmount"]?.toDecimal(), value.chargeAmount),
                MatcherResult.test(::equal, dto["chargeCycle"]?.toInt(), value.chargeCycle),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired),
                MatcherResult.test(::equal, dto["updateURL"]?.content(), value.updateURL),
                MatcherResult.test(::stepUpFrom, dto["stepUp"], value.stepUp),
            )
    }

fun googlePayDetailsFrom(dto: JsonElement): Matcher<GooglePayDetails> =
    googlePayDetailsFrom(dto.jsonObject)

fun googlePayDetailsFrom(dto: JsonObject): Matcher<GooglePayDetails> =
    object : Matcher<GooglePayDetails> {
        override fun test(value: GooglePayDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired),
                MatcherResult.test(::stepUpFrom, dto["stepUp"], value.stepUp),
            )
    }

fun applePayDetailsFrom(dto: JsonElement): Matcher<ApplePayDetails> =
    applePayDetailsFrom(dto.jsonObject)

fun applePayDetailsFrom(dto: JsonObject): Matcher<ApplePayDetails> =
    object : Matcher<ApplePayDetails> {
        override fun test(value: ApplePayDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::stepUpFrom, dto["stepUp"], value.stepUp),
            )
    }
