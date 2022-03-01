package au.com.woolworths.village.sdk

import au.com.redcrew.apisdkcreator.httpclient.HTTP_CLIENT_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.MARSHALLING_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.redcrew.apisdkcreator.httpclient.UNMARSHALLING_ERROR_TYPE

const val MISSING_PROP_ERROR_TYPE = "missing-prop"

fun missingProp(name: String): SdkError =
    SdkError(MISSING_PROP_ERROR_TYPE, "'${name}' is mandatory and missing")

fun toApiError(error: SdkError): ApiError =
    when(error.type) {
        HTTP_CLIENT_ERROR_TYPE -> HttpClientError(error.message, error.cause)
        MARSHALLING_ERROR_TYPE -> JsonMarshallingError(error.message, error.cause)
        UNMARSHALLING_ERROR_TYPE-> JsonUnmarshallingError(error.message, error.cause)
        MISSING_PROP_ERROR_TYPE -> JsonUnmarshallingError(error.message, error)
        else -> ApiError(error.message, error)
    }

fun toApiErrorResult(error: SdkError): ApiResult.Error =
    ApiResult.Error(toApiError(error))