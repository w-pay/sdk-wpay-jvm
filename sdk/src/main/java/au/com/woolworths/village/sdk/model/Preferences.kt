package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Available preference settings for a customer
 */
interface CustomerPreferences : Serializable {
    /** Payment preferences for a customer */
    val payments: PaymentPreferences?

    /** Map of general preferences. */
    val general: Preferences?
}

/**
 * Payment preferences for a customer
 */
interface PaymentPreferences : Serializable {
    /**
     * The primary instrument that will be used by default for a payment if a specific instrument is not specified.
     *
     * The primary instrument will be used for the balance of the payment after any specified secondary instruments are used first.
     */
    val primaryInstrumentId: String?

    /**
     * Rules for the creation of a default set of secondary instruments to be used for a payment if a specific set is not specified.
     *
     *  Secondary instruments are used in order until the full amount of the payment has been paid.
     */
    val secondaryInstruments: SecondaryInstrumentPreferences?
}

interface SecondaryInstrumentPreferences : Serializable {
    /**
     * Flag indicating whether secondary instruments are enabled or disabled.
     *
     * If not present defaults to enabled. Used to specifically disable secondary instruments without losing customer configure preferences.
     */
    val enableSecondaryInstruments: Boolean?

    /** The order that the secondary instruments will be used for a specific payment. */
    val order: SecondaryInstrumentOrder?

    /**
     * Array of instruments to exclude from the secondary instruments set.
     *
     * Indicates that the set of secondary instruments should include all valid instruments excluding those in this list.
     */
    val exclude: List<String>?

    /**
     * Array of instruments to specifically include in the secondary instruments set.
     *
     * Indicates that the set of secondary instruments should start as empty and only include all valid instruments excluding those in this list.
     */
    val include: List<String>?
}

enum class SecondaryInstrumentOrder {
    BALANCE_ASC,
    BALANCE_DESC,
    EXPIRY_ASC,
    EXPIRY_DESC,
    FIFO,
    LIFO,
    INCLUDE_ORDER
}

/**
 * Map of general preferences.
 */
typealias Preferences = Map<String, Map<String, String>>
