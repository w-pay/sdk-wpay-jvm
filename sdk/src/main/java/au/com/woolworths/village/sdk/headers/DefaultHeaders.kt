package au.com.woolworths.village.sdk.headers

import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.RequestHeaderFactory
import au.com.redcrew.apisdkcreator.httpclient.RequestHeadersFactory
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.redcrew.apisdkcreator.httpclient.bearerToken
import au.com.redcrew.apisdkcreator.httpclient.constantHeaders
import au.com.redcrew.apisdkcreator.httpclient.createHeaders
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken
import au.com.woolworths.village.sdk.auth.toApiAuthenticator

/*
 * Kotlin does not support runtime polymorphic dispatch for functions.
 * So we have to have different functions with difference names and signatures,
 * and compose them together.
 */

const val AUTHENTICATION_ERROR = "authentication-error"

fun apiAuthenticatorToRequestHeaderFactory(
    token: ApiAuthenticator<HasAccessToken>
): RequestHeaderFactory =
    bearerToken({ token.authenticate().toEither().bimap(
        { SdkError(AUTHENTICATION_ERROR, it.toString()) },
        { it.accessToken }
    )}, AUTHORISATION)

fun accessTokenHeader(opts: WPayOptions): RequestHeaderFactory =
    when(opts.accessToken) {
        is ApiTokenType.NoToken -> { _ -> emptyMap<String, String>().right() }
        else -> apiAuthenticatorToRequestHeaderFactory(toApiAuthenticator(opts.accessToken))
    }

fun constantCustomerOptsToHeaders(opts: WPayCustomerOptions): RequestHeaderFactory =
    constantHeaders(buildMap {
        opts.walletId?.let { put(X_WALLET_ID, it) }
    })

fun constantMerchantOptsToHeaders(opts: WPayMerchantOptions): RequestHeaderFactory =
    constantHeaders(buildMap {
        opts.merchantId?.let { put(X_MERCHANT_ID, it) }
    })

fun constantOptsToHeaders(opts: WPayOptions): RequestHeaderFactory {
    return constantHeaders(buildMap {
        put(X_API_KEY, opts.apiKey)
        put(X_EVERYDAY_PAY_WALLET, when (opts.wallet) {
            Wallet.MERCHANT -> "false"
            Wallet.EVERYDAY_PAY -> "true"
        })
    })
}

fun defaultHeaders(opts: WPayOptions): RequestHeadersFactory =
    createHeaders(listOf(
        constantOptsToHeaders(opts),
        accessTokenHeader(opts)
    ))

fun defaultCustomerHeaders(opts: WPayCustomerOptions): RequestHeadersFactory =
    createHeaders(listOf(
        constantOptsToHeaders(opts),
        constantCustomerOptsToHeaders(opts),
        accessTokenHeader(opts)
    ))

fun defaultMerchantHeaders(opts: WPayMerchantOptions): RequestHeadersFactory =
    createHeaders(listOf(
        constantOptsToHeaders(opts),
        constantMerchantOptsToHeaders(opts),
        accessTokenHeader(opts)
    ))