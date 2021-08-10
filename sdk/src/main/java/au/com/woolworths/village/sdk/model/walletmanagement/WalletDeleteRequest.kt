package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface WalletDeleteRequest : Serializable {
    /* The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String

    /* The merchant shopper id of the user. */
    val shopperId: String
}
