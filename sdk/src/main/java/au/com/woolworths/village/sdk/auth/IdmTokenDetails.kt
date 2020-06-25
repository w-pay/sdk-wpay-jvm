package au.com.woolworths.village.sdk.auth

class IdmTokenDetails(
    val accessToken: String,
    val accessTokenExpiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
    val tokensIssuedAt: Long,
    val isGuestToken: Boolean,
    val idmStatusOK: Boolean
)