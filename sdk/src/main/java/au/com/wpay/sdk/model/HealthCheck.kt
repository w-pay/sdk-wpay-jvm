package au.com.wpay.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonNames
import java.io.Serializable

/**
 * Result of checking the health of the API server
 */
@Suppress("EXPERIMENTAL_API_USAGE")
@kotlinx.serialization.Serializable
data class HealthCheck(
    /** Health check result */
    @SerialName("healthCheck")
    val result: Status
) : Serializable {
    /**
     * Possible health states that the API server can be in.
     */
    @kotlinx.serialization.Serializable
    enum class Status {
        /** The API server is healthy */
        @SerialName("SUCCESS")
        @JsonNames("success")
        SUCCESS
    }
}
