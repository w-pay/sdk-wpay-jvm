package au.com.woolworths.village.sdk

/**
 * What can be configured in the SDK post SDK initialisation.
 */
interface Configurable {
    /**
     * Sets the API host that the SDK uses.
     *
     * @param host The host (including protocol and port) of the API server
     */
    fun setHost(host: String)
}