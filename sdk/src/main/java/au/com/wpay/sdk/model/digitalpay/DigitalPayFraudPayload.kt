package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

/**
 * Fraud check response
 */
@Serializable
data class DigitalPayFraudPayload(
    /** The fraud check provider. */
    val provider: String,

    /** The fraud check version. */
    val version: String,

    /** The fraud check message format. */
    val format: DigitalPayFraudMessageFormat,

    /** The fraud check message format. */
    val responseFormat: DigitalPayFraudMessageFormat,

    /** The fraud check message. */
    val message: String
) : ModelType

enum class DigitalPayFraudMessageFormat {
    ZIP_BASE_64_ENCODED,
    XML
}
