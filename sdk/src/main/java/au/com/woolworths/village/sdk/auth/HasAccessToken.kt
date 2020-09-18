package au.com.woolworths.village.sdk.auth

/**
 * Indicates that a model has an access token that can be used to prove that SDK is authenticated
 */
interface HasAccessToken {
    /** An access token obtained from an authentication result */
    val accessToken: String
}