package au.com.woolworths.village.sdk

import arrow.core.Either
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.redcrew.apisdkcreator.httpclient.UNMARSHALLING_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class Person(val name: String)

data class Address(val street: String)

fun dataFrom(json: String): UnstructuredData =
    UnstructuredData.String(json)

fun identifyTransform(json: JsonObject): Either<SdkError, JsonObject> = json.right()

class KotlinxSerializationAdapterTest: DescribeSpec({
    describe("kotlinx-serialization adapter") {
        val unmashallerFactory = kotlinxSerialisationUnmarshaller()

        it("should unmarshall JSON to object") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(::identifyTransform)(Person::class)(
                dataFrom("""{ "name": "$name" }""")
            )

            result.shouldBeRight(Person(name))
        }

        it("should return error when unmarshalling fails") {
            val result = unmashallerFactory(::identifyTransform)(Address::class)(
                dataFrom("""{ "street": "Wayne Way" }""")
            )

            val error = result.shouldBeLeft()
            error.type.shouldBe(UNMARSHALLING_ERROR_TYPE)
            error.message.shouldContain("Serializer for class 'Address' is not found.")
            error.cause.shouldNotBe(null)
        }

        it("should ignore missing keys") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(::identifyTransform)(Person::class)(
                dataFrom("""{ "name": "$name", "address": { "street": "Wayne Way" } }""")
            )

            result.shouldBeRight(Person(name))
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