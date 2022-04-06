package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.GiftCardsBalanceRequest
import au.com.wpay.sdk.model.walletmanagement.GiftCardsBalanceResponse
import au.com.wpay.sdk.model.walletmanagement.TokenizeGiftCardRequest
import au.com.wpay.sdk.model.walletmanagement.TokenizeGiftCardResponse

class GiftCardsApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a payment instrument id for a provided gift card.
     *
     * @param tokenizeGiftCardRequest Detail of the Gift Card to be tokenized.
     */
    suspend fun tokenize(tokenizeGiftCardRequest: TokenizeGiftCardRequest): ApiResult<TokenizeGiftCardResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizeGiftCardRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeGiftCardResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/giftcards/tokenize"),
            body = tokenizeGiftCardRequest
        )))
    }

    /**
     * Create a paymment intrument id for a provided gift card of a guest user.
     *
     * @param tokenizeGiftCardRequest Detail of the Gift Card to be tokenized.
     */
    suspend fun guestTokenize(
        tokenizeGiftCardRequest: TokenizeGiftCardRequest
    ): ApiResult<TokenizeGiftCardResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizeGiftCardRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeGiftCardResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/giftcards/tokenize"),
            body = tokenizeGiftCardRequest
        )))
    }

    /**
     * Get the balance and expiryinfo for the provided gift cards. This API is rate limited to 5 requests per minute per shopper id.
     *
     * @param giftcardsBalanceRequest Detail of the Gift Card to recieve balences for.
     */
    suspend fun balance(giftcardsBalanceRequest: GiftCardsBalanceRequest): ApiResult<GiftCardsBalanceResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: GiftCardsBalanceRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<GiftCardsBalanceResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/giftcards/balance"),
            body = giftcardsBalanceRequest
        )))
    }
}
