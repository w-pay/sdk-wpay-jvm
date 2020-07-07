package au.com.woolworths.village.sdk.model

interface MerchantSchemas {
}

interface MerchantSchemaSummaries: MerchantSchemas {
    fun schemas(): List<MerchantSchemaSummary>
}

interface MerchantSchemaSummary {
    fun schemaId(): String

    fun type(): String

    fun description(): String?
}

interface MerchantSchema {
    fun schema():  Map<String, Any>

    fun type(): String?

    fun description(): String?
}