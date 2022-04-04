package au.com.wpay.sdk.headers

import arrow.core.Either
import au.com.redcrew.apisdkcreator.httpclient.HttpHeaders
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.wpay.sdk.*
import au.com.wpay.sdk.auth.ApiAuthenticator
import au.com.wpay.sdk.auth.HasAccessToken
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.maps.shouldNotContainKey

const val apiKey = "abc123"
const val baseUrl = "http://localhost"

val defaultOptions = WPayOptions(apiKey, baseUrl)
val customerOptions = WPayCustomerOptions(apiKey, baseUrl, "12345678")
val merchantOptions = WPayMerchantOptions(apiKey, baseUrl, "987654321")

class DefaultHeadersTest : DescribeSpec({
    suspend fun getHeaders(opts: WPayOptions): Either<SdkError, HttpHeaders> =
        defaultHeaders(opts)()

    suspend fun getCustomerHeaders(opts: WPayCustomerOptions): Either<SdkError, HttpHeaders> =
        defaultCustomerHeaders(opts)()

    suspend fun getMerchantHeaders(opts: WPayMerchantOptions): Either<SdkError, HttpHeaders> =
        defaultMerchantHeaders(opts)()

    describe("default headers") {
        describe("api key") {
            val opts = defaultOptions

            it("should add apiKey header") {
                val headers = getHeaders(opts).shouldBeRight()
                headers.shouldContain(X_API_KEY, opts.apiKey)
            }
        }

        describe("bearer token") {
            val accessToken = "abc123def456"

            it("should add authorisation header from string token") {
                val opts = WPayOptions(apiKey, baseUrl, ApiTokenType.StringToken(accessToken))

                val headers = getHeaders(opts).shouldBeRight()
                headers.shouldContain(AUTHORISATION, "Bearer $accessToken")
            }

            it("should add authorisation header from ApiAuthenticator") {
                val opts = WPayOptions(apiKey, baseUrl, ApiTokenType.ApiAuthenticatorToken(
                    object : ApiAuthenticator<HasAccessToken> {
                        override fun authenticate(): ApiResult<HasAccessToken> =
                            ApiResult.Success(object : HasAccessToken {
                                override val accessToken: String
                                    get() = accessToken
                            })
                    }
                ))

                val headers = getHeaders(opts).shouldBeRight()
                headers.shouldContain(AUTHORISATION, "Bearer $accessToken")
            }

            it("should ignore missing access token") {
                val opts = WPayOptions(apiKey, baseUrl)

                val headers = getHeaders(opts).shouldBeRight()
                headers.shouldNotContainKey(AUTHORISATION)
            }
        }

        describe("optional headers") {
            describe("customer options") {
                val opts = customerOptions

                it("should add walletId header") {
                    val headers = getCustomerHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_WALLET_ID, opts.walletId)
                }

                it("should ignore missing walletId") {
                    @Suppress("NAME_SHADOWING")
                    val opts = WPayCustomerOptions(apiKey, baseUrl)

                    val headers = getCustomerHeaders(opts).shouldBeRight()
                    headers.shouldNotContainKey(X_WALLET_ID)
                }

                it("should compose with default options") {
                    val headers = getCustomerHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_API_KEY, apiKey)
                }
            }

            describe("merchant options") {
                val opts = merchantOptions

                it("should add merchantId header") {
                    val headers = getMerchantHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_MERCHANT_ID, opts.merchantId)
                }

                it("should ignore missing merchantId") {
                    @Suppress("NAME_SHADOWING")
                    val opts = WPayMerchantOptions(apiKey, baseUrl)

                    val headers = getMerchantHeaders(opts).shouldBeRight()
                    headers.shouldNotContainKey(X_MERCHANT_ID)
                }

                it("should compose with default options") {
                    val headers = getMerchantHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_API_KEY, apiKey)
                }
            }

            describe("wallet") {
                it("should set header to true if wallet everyday pay") {
                    val opts = WPayOptions(apiKey, baseUrl, Wallet.EVERYDAY_PAY)

                    val headers = getHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_EVERYDAY_PAY_WALLET, "true")
                }

                it("should set header to false if wallet not everyday pay") {
                    val opts = WPayOptions(apiKey, baseUrl, Wallet.MERCHANT)

                    val headers = getHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_EVERYDAY_PAY_WALLET, "false")
                }

                it("should default header to merchant if no wallet given") {
                    val opts = WPayOptions(apiKey, baseUrl)

                    val headers = getHeaders(opts).shouldBeRight()
                    headers.shouldContain(X_EVERYDAY_PAY_WALLET, "false")
                }
            }
        }
    }
})
