package au.com.wpay.sdk

import arrow.core.*
import au.com.redcrew.apisdkcreator.httpclient.*
import au.com.redcrew.apisdkcreator.httpclient.kotlin.GenericTypeCurriedFunction
import au.com.wpay.sdk.model.ChallengeResponse
import au.com.wpay.sdk.model.FraudPayload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * The shape of the data returned from the API doesn't match the shape of the types returned from
 * SDK operations. This is because the SDK types are designed to make integrating with a client
 * app easier (taking into account language and platform requirements) whereas the API response
 * is a representation of the data in the server system.
 *
 * For example, let's suppose the SDK has a `Person` type
 *
 * ```
 * data class Person(val name: String, val address: String)
 * ```
 *
 * The API response data may look like:
 * ```
 * {
 *   "data": {
 *     "name": "Bruce Wayne",
 *     "address": "Wayne Manor"
 *   },
 *   "meta": {}
 * }
 * ```
 *
 * Or there may be props in the meta block that need to be part of the SDK type as well.
 * For example:
 * ```
 * {
 *   "data": {
 *     "name": "Bruce Wayne"
 *   },
 *   "meta": {
 *     "address": "Wayne Manor"
 *   }
 * }
 * ```
 *
 * Due to the misalignment between the shape of the API data and the SDK model types, we need to
 * preprocess the JSON data before getting kotlinx-serialization to unmarshall (deserialise) the
 * JSON into a Kotlin object instance.
 *
 * Due to kotlinx-serialization's transformation features being dependent on compile time binding
 * through the use of generics and annotations, there is no easy way to bind a type to a
 * transformation via annotations without having to replicate the same process for every type in
 * the SDK. This is unwieldy given the amount of types in the SDK.
 *
 * We also can't use a wrapper class as (to use the second example above) we can't construct a
 * valid Person object without both the name and address. Therefore, we would have to create a
 * "partial Person" type or weaken constraints in the Person type (eg: make address nullable).
 * Weakening constraints leads to an anemic model and is not a good long term approach.
 *
 * Therefore, we have a transformer type that can, at runtime process the JSON data and give the data
 * to kotlinx-serialisation when ready.
 *
 * Being a function type, complex transformations can be built if required. If identity is required,
 * the `jsonPassthrough` function can be used.
 */
typealias JsonTransformer = suspend (JsonObject) -> Either<SdkError, JsonObject>

/**
 * At the point when the request data type is known (typically in the implementation of an API operation) the
 * request data needs to be encoded into UnstructuredData. In order to avoid needing to use reflection, an encoding
 * function can be used that itself receives a Json instance and the data to be encoded. The [JsonEncoder] can then
 * try to encode the data.
 *
 * A helper function [tryEncoding] can be used to catch exceptions and convert to [SdkError]s
 */
typealias JsonEncoder<T> = suspend (Json, T) -> Either<SdkError, UnstructuredData>

/**
 * At a point when the response data type is known (typically in the implementation of an API operation) the
 * JSON needs to be decoded into an instance of that type. In order to avoid needing to use reflection, a decoding
 * function can be used that itself receives a Json instance and the parsed JSON data (JsonElement). The [JsonDecoder]
 * can then try to decode the data.
 *
 * A helper function [tryDecoding] can be used to catch exceptions and convert to [SdkError]s
 */
typealias JsonDecoder<T> = suspend (Json, JsonElement) -> Either<SdkError, T>

/**
 * A Marshaller that uses a [JsonEncoder] to encode JSON data to an instance of a type.
 */
// EncodingMarshaller :: (JsonEncoder<T>) -> Marshaller
interface EncodingMarshaller: GenericTypeCurriedFunction {
    suspend operator fun <T : Any> invoke(p1: JsonEncoder<T>): Marshaller
}

/**
 * An Unmarshaller that uses a [JsonDecoder] to decode JSON data to an instance of a type.
 */
// DecodingUnmarshaller :: (JsonDecoder<T>) -> Unmarshaller<T>
interface DecodingUnmarshaller: GenericTypeCurriedFunction {
    suspend operator fun <T : Any> invoke(p1: JsonDecoder<T>): Unmarshaller<T>
}

/**
 * A [JsonEncoder] that can be used when there is no HttpRequest body. Returns an error if invoked.
 */
val unitEncoder: JsonEncoder<Unit> = { _, _ -> SdkError(MARSHALLING_ERROR_TYPE, "Can't encode an empty body").left() }

/**
 * A [JsonDecoder] that can be used when there is no HttpRequest body. Returns an error if invoked.
 */
val unitDecoder: JsonDecoder<Unit> = { _, _ -> SdkError(MARSHALLING_ERROR_TYPE, "Can't decode an empty body").left() }

/**
 * An alias to allow the future evolution of the SDK without having to update every "Api" class.
 */
typealias SdkJsonMarshaller = EncodingMarshaller

/**
 * An SDK JSON Unmarshaller transforms then unmarshalls the JSON response into a known type.
 */
// SdkJsonUnmarshaller :: (JsonTransformer) -> JsonDecoder<T> -> Unmarshaller<T>
interface SdkJsonUnmarshaller {
    operator fun invoke(p1: JsonTransformer): DecodingUnmarshaller
}

@Suppress("EXPERIMENTAL_API_USAGE")
private val parser = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
}

fun kotlinxSerialisationMarshaller(): SdkJsonMarshaller =
    object : EncodingMarshaller {
        @Suppress("UNCHECKED_CAST")
        override suspend fun <T : Any> invoke(p1: JsonEncoder<T>): Marshaller =
            { p1(parser, it as T) }
    }

fun kotlinxSerialisationUnmarshaller(): SdkJsonUnmarshaller =
    object: SdkJsonUnmarshaller {
        override fun invoke(p1: JsonTransformer): DecodingUnmarshaller =
            object : DecodingUnmarshaller {
                override suspend fun <T : Any> invoke(p1: JsonDecoder<T>): Unmarshaller<T> =
                    { data: UnstructuredData ->
                        @Suppress("REDUNDANT_ELSE_IN_WHEN")
                        when (data) {
                            is UnstructuredData.String -> unmarshallString(p1, data.data)

                            // future proofing
                            else -> SdkError(ILLEGAL_STATE_ERROR_TYPE, "Unrecognised unstructured data type").left()
                        }
                    }

                    suspend fun <T : Any> unmarshallString(deserialiser: JsonDecoder<T>, data: String): Either<SdkError, T> =
                        try {
                            val json = parser.parseToJsonElement(data)

                            p1(json.jsonObject)
                                .flatMap { deserialiser(parser, it) }
                        }
                        catch (e: Exception) {
                            SdkError(UNMARSHALLING_ERROR_TYPE, e.message!!, e).left()
                        }
            }
    }

/**
 * Helper function to catch exceptions when encoding data to JSON and return the error.
 */
inline fun <reified T> tryEncoding(parser: Json, data: T): Either<SdkError, UnstructuredData> =
    Either.catch(
        { SdkError(UNMARSHALLING_ERROR_TYPE, it.message!!, it) },
        { UnstructuredData.String(parser.encodeToString(data)) }
    )


/**
 * Helper function to catch exceptions when decoding JsonElements and return the error.
 */
inline fun <reified T> tryDecoding(parser: Json, el: JsonElement): Either<SdkError, T> =
    Either.catch(
        { SdkError(UNMARSHALLING_ERROR_TYPE, it.message!!, it) },
        { parser.decodeFromJsonElement(el) }
    )

/*
 * Only for use with HttpRequest's.
 */
@Serializable
internal data class Meta(
    val fraud: FraudPayload?,
    val challengeResponses: List<ChallengeResponse> = emptyList(),
) {
    constructor(): this(null)
}

/*
 * Only for use with HttpRequest's to give the HTTP request body to kotlinx-serialization in the
 * message structure the API server is expecting.
 */
@Serializable
internal data class ApiRequestBody<D, M>(
    val data: D,
    val meta: M
)

fun OffsetDateTime.toIsoDateTime(): String =
    this.withNano(0).format(DateTimeFormatter.ISO_DATE_TIME)

object ISODateSerializer : KSerializer<OffsetDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ISODate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: OffsetDateTime) =
        encoder.encodeString(value.toIsoDateTime())

    override fun deserialize(decoder: Decoder): OffsetDateTime =
        OffsetDateTime.parse(decoder.decodeString())
}

open class DecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal) =
        encoder.encodeDouble(value.toDouble())

    override fun deserialize(decoder: Decoder): BigDecimal =
        decoder.decodeDouble().toBigDecimal()
}

object CurrencySerializer : DecimalSerializer() {
   override fun serialize(encoder: Encoder, value: BigDecimal) =
       super.serialize(encoder, value.setScale(2, RoundingMode.HALF_EVEN))
}

fun jsonPassthrough(json: JsonObject): Either<SdkError, JsonObject> =
    json.right()

fun fromProp(prop: String, json: JsonObject): Either<SdkError, JsonObject> =
    Either
        .fromNullable(json[prop]?.jsonObject)
        .bimap({ missingProp(prop) }, ::identity)

fun fromData(json: JsonObject): Either<SdkError, JsonObject> =
    fromProp("data", json)

fun fromMeta(json: JsonObject): Either<SdkError, JsonObject> =
    fromProp("meta", json)
