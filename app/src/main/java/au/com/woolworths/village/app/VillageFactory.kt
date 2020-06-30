package au.com.woolworths.village.app

import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.auth.CustomerLoginApiAuthenticator
import au.com.woolworths.village.sdk.auth.IdmTokenDetails
import au.com.woolworths.village.sdk.auth.StoringApiAuthenticator
import au.com.woolworths.village.sdk.openapi.OpenApiVillageApiRepository

fun createVillage(): Village<IdmTokenDetails> {
    val options = VillageOptions("haTdoUWVhnXm5n75u6d0VG67vCCvKjQC")
    val apiKeyRequestHeader = ApiKeyRequestHeader(options)
    val bearerTokenRequestHeader = BearerTokenRequestHeader()
    val api =
        OpenApiVillageApiRepository(
            RequestHeaderChain(
                arrayOf(
                    apiKeyRequestHeader,
                    bearerTokenRequestHeader
                )
            ),
            BuildConfig.API_CONTEXT_ROOT
        )

    val customerLogin = CustomerLoginApiAuthenticator(
        RequestHeaderChain(arrayOf(apiKeyRequestHeader)),
        "/wow/v1/idm/servers/token"
    )
    val authentication = StoringApiAuthenticator(customerLogin, bearerTokenRequestHeader)

    return Village(api, authentication)
}