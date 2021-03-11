package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Fraud check response
 *
 * @category Model
 */
interface DigitalPayFraudPayload: Serializable {
	/* The fraud check provider. */
	val provider: String

	/* The fraud check version. */
	val version: String

	/* The fraud check message format. */
	val format: DigitalPayFraudMessageFormat

	/* The fraud check message format. */
	val responseFormat: DigitalPayFraudMessageFormat

	/* The fraud check message. */
	val message: String
}

enum class DigitalPayFraudMessageFormat{
	ZIP_BASE_64_ENCODED,
	XML
}