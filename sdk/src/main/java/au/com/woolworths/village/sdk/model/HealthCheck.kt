package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Result of checking the health of the API server
 */
interface HealthCheck : Serializable {
    /**
     * Possible health states that the API server can be in.
     */
    enum class Status {
        /** The API server is healthy */
        SUCCESS
    }

    /** Health check result */
    val result: Status
}