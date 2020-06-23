package au.com.woolworths.village.sdk

class BearerTokenRequestHeader: RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        // TODO: Update with a real way to set bearer tokens
        headers["Authorization"] = "Bearer ODA4NTYyNDktNjg0Ny00OWY4LWFmMDItOTU1MWEwMzliMjg5OlZJTExBR0VfQ1VTVE9NRVI="
    }
}