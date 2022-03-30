package au.com.woolworths.village.sdk.model.walletmanagement

import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class WalletDeleteRequest(
    /* The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String,

    /* The merchant shopper id of the user. */
    val shopperId: String
) : ModelType
