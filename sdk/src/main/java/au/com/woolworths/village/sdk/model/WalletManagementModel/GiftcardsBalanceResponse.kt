package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the GiftcardsApi Balance endpoint.
 *
 * @category Model
 */
interface GiftcardsBalanceResponse: Serializable {
	val giftCardBalances List<GiftCardBalance>
}

interface GiftCardBalance: Serializable {
	/* The gift card number. This property will only be returned if the endpoint was called with the "cardNumber" and "pinCode" request properties. */
	val cardNumber String

	/* The gift card payment instrument id. This property will only be returned if the endpoint was called with the "paymentInstrumentId" request property. */
	val paymentInstrumentId String

	/* The current available balance of the gift card. */
	val balance BigDecimal

	/*  The day of the expiry date of the gift card. */
	val expiryDay String

	/* The month of the expiry date of the gift card. */
	val expiryMonth String

	/* The year of the expiry date of the gift card. */
	val expiryYear String

	/* A flag to indicate if the gift card is expired. */
	val expired Boolean?
}
