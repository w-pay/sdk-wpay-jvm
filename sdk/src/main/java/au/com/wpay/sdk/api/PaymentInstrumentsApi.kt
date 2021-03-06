package au.com.wpay.sdk.api

import arrow.core.Either
import arrow.core.Option
import arrow.core.computations.either
import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.wpay.sdk.*
import au.com.wpay.sdk.helpers.optionalParam
import au.com.wpay.sdk.helpers.params
import au.com.wpay.sdk.model.*
import kotlinx.serialization.json.JsonObject

private suspend fun fromGetByTokenResponse(dto: JsonObject): Either<SdkError, JsonObject> =
    either {
        val data = fromData(dto).bind()
        val meta = fromMeta(dto).bind()

        Option
            .fromNullable(meta["cipherText"])
            .fold(
                { data },
                { JsonObject(buildMap {
                    putAll(data)
                    put("cipherText", it)
                }) }
            )
    }


class PaymentInstrumentsApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Get the specified payment instrument of the customer.
     *
     * @param paymentToken The payment token of the payment instrument to fetch.
     * @param publicKey A public key used to encrypt sensitive instrument data and include that encrypted data in the response sent back to the consumer.
     */
    suspend fun getByToken(
        paymentToken: String,
        publicKey: String? = null
    ): ApiResult<IndividualPaymentInstrument> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromGetByTokenResponse)({ parser, el -> tryDecoding<IndividualPaymentInstrument>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/instruments/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = params(optionalParam("publicKey", publicKey)),
            body = null
        )))
    }

    /**
     * Retrieve the customer's registered [PaymentInstrument]s
     */
    suspend fun list(): ApiResult<WalletContents> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<WalletContents>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/instruments")
        )))
    }

    /**
     * Delete a [PaymentInstrument} from a {@link Wallet]
     *
     * @param instrument The payment instrument to delete.
     */
    suspend fun delete(instrument: String): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(marshall(unitEncoder), unmarshall(::jsonPassthrough)(unitDecoder))

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/customer/instruments/:paymentInstrumentId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentInstrumentId" to instrument
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Initiate the addition of a new [PaymentInstrument] for the customer.
     *
     * To complete the addition the customer will have to use the returned URL details to enter
     * the instrument details.
     *
     * @param instrument Initial details to begin the addition workflow.
     */
    suspend fun initiateAddition(
        instrument: PaymentInstrumentAddition
    ): ApiResult<PaymentInstrumentAdditionResult> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<PaymentInstrumentAddition, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentInstrumentAdditionResult>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/instruments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = instrument,
                meta = Meta()
            )
        )))
    }
}
