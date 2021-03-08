package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Fraud check response
 *
 * @category Model
 */
interface DigitalPayFraudPayload: Serializable {
	/* The fraud check provider. */
	val provider String

	/* The fraud check version. */
	val version String

	/* The fraud check message format. */
	val format MessageFormat

	/* The fraud check message format. */
	val responseFormat MessageFormat

	/* The fraud check message. */
	val message String
}

enum class MessageFormat{
	ZIP_BASE_64_ENCODED,
	XML
}