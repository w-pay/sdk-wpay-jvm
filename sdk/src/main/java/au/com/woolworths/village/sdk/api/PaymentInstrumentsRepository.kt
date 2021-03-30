package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.PaymentInstrumentAddition
import au.com.woolworths.village.sdk.model.PaymentInstrumentAdditionResult
import au.com.woolworths.village.sdk.model.WalletContents

interface PaymentInstrumentsRepository {
    /**
     * Retrieve the customer's registered [PaymentInstrument]s
     */
    fun list(): ApiResult<WalletContents>

    /**
     * Delete a [PaymentInstrument} from a {@link Wallet]
     *
     * @param instrument The payment instrument to delete.
     */
    fun delete(instrument: String): ApiResult<Unit>

    /**
     * Initiate the addition of a new [PaymentInstrument] for the customer.
     *
     * To complete the addition the customer will have to use the returned URL details to enter
     * the instrument details.
     *
     * @param instrument Initial details to begin the addition workflow.
     */
    fun initiateAddition(
        instrument: PaymentInstrumentAddition
    ): ApiResult<PaymentInstrumentAdditionResult>
}