package au.com.wpay.sdk

import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.redcrew.apisdkcreator.httpclient.UNMARSHALLING_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.matchers.content
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import java.math.BigDecimal

@Serializable
data class Person(val name: String)

data class Address(val street: String)

fun dataFrom(json: String): UnstructuredData =
    UnstructuredData.String(json)

@Suppress("EXPERIMENTAL_API_USAGE")
private val JSON = Json { explicitNulls = false }

class KotlinxSerializationAdapterTest: DescribeSpec({
    describe("marshalling") {
        val marshaller = kotlinxSerialisationMarshaller()

        it("should marshall data") {
            val name = "Bruce Wayne"

            marshaller(Person(name))
                .shouldBeRight(UnstructuredData.String("""{"name":"$name"}"""))
        }
    }

    describe("unmarshalling") {
        val unmashallerFactory = kotlinxSerialisationUnmarshaller()

        it("should unmarshall JSON to object") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(::jsonPassthrough)(Person::class)(
                dataFrom("""{ "name": "$name" }""")
            )

            result.shouldBeRight(Person(name))
        }

        it("should return error when unmarshalling fails") {
            val result = unmashallerFactory(::jsonPassthrough)(Address::class)(
                dataFrom("""{ "street": "Wayne Way" }""")
            )

            val error = result.shouldBeLeft()
            error.type.shouldBe(UNMARSHALLING_ERROR_TYPE)
            error.message.shouldContain("Serializer for class 'Address' is not found.")
            error.cause.shouldNotBe(null)
        }

        it("should ignore missing keys") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(::jsonPassthrough)(Person::class)(
                dataFrom("""{ "name": "$name", "address": { "street": "Wayne Way" } }""")
            )

            result.shouldBeRight(Person(name))
        }
    }

    describe("serializers") {
        describe("ISODateSerializer") {
            @Serializable
            data class ISODateSerializerStub(
                @Serializable(with = ISODateSerializer::class)
                val date: OffsetDateTime
            )

            val now = OffsetDateTime.of(2022, 3, 1, 15, 14, 34, 333, ZoneOffset.UTC)

            it("should serialise date to ISO string") {
                val result = JSON.encodeToJsonElement(ISODateSerializerStub(now)).jsonObject

                result["date"]?.content().shouldBe("2022-03-01T15:14:34Z")
            }

            it("should deserialise ISO date string") {
                val result = JSON.decodeFromJsonElement<ISODateSerializerStub>(JsonObject(mapOf(
                    "date" to JsonPrimitive("2022-03-01T15:14:34Z")
                )))

                result.date.shouldBe(now.withNano(0))
            }
        }

        describe("CurrencySerializer") {
            @Serializable
            data class CurrencySerializerStub(
                @Serializable(with = CurrencySerializer::class)
                val amount: BigDecimal
            )

            val amount = BigDecimal("12.232")

            it("should serialise decimal to currency string") {
                val result = JSON.encodeToJsonElement(CurrencySerializerStub(amount)).jsonObject

                result["amount"]?.content().shouldBe("12.23")
            }

            it("should round correctly") {
                val result = JSON.encodeToJsonElement(CurrencySerializerStub(BigDecimal("12.235"))).jsonObject

                result["amount"]?.content().shouldBe("12.24")
            }

            it("should deserialise currency string") {
                val result = JSON.decodeFromJsonElement<CurrencySerializerStub>(JsonObject(mapOf(
                    "amount" to JsonPrimitive("12.232")
                )))

                result.amount.shouldBe(amount)
            }
        }
    }

    describe("api message structure") {
        @Serializable
        data class Person(val name: String, val address: String)

        val name = "Bruce Wayne"
        val address = "Wayne Manor"

        it("should serialise to message structure") {
            val person = Person(name, address)

            val result = JSON.encodeToJsonElement(ApiRequestBody(person, Meta())).jsonObject

            result["data"]?.jsonObject.shouldBe(
                JsonObject(mapOf(
                "name" to JsonPrimitive(name),
                "address" to JsonPrimitive(address)
            )))

            result["meta"]?.toString().shouldBe("{}")
        }
    }

    describe("fromData") {
        it("should return data from input") {
            val data = JsonObject(mapOf("key" to JsonPrimitive("value")))
            val input = JsonObject(mapOf("data" to data))

            val result = fromData(input)

            result.shouldBeRight(data)
        }

        it("should return error if data prop not found") {
            val result = fromData(JsonObject(emptyMap()))

            result.shouldBeLeft(
                SdkError(MISSING_PROP_ERROR_TYPE, "'data' is mandatory and missing")
            )
        }
    }
})
