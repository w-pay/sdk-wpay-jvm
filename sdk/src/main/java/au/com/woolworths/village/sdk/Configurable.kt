package au.com.woolworths.village.sdk

interface Configurable {
    fun setHost(host: String)

    fun setContextRoot(contextRoot: String)
}