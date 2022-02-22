package au.com.woolworths.village.sdk

import au.com.redcrew.apisdkcreator.httpclient.UNMARSHALLING_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import kotlinx.serialization.Serializable

@Serializable
data class Person(val name: String)

data class Address(val street: String)

fun dataFrom(json: String): UnstructuredData =
    UnstructuredData.String(json)

class KotlinxSerializationAdapterTest: DescribeSpec({
    describe("kotlinx-serialization adapter") {
        val unmashallerFactory = kotlinxSerialisationUnmarshaller()

        it("should unmarshall JSON to object") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(Person::class)(dataFrom("""{ "name": "$name" }"""))

            result.shouldBeRight(Person(name))
        }

        it("should return error when unmarshalling fails") {
            val result = unmashallerFactory(Address::class)(dataFrom("""{ "street": "Wayne Way" }"""))

            val error = result.shouldBeLeft()
            error.type.shouldBe(UNMARSHALLING_ERROR_TYPE)
            error.message.shouldContain("Serializer for class 'Address' is not found.")
            error.cause.shouldNotBe(null)
        }

        it("should ignore missing keys") {
            val name = "Bruce Wayne"
            val result = unmashallerFactory(Person::class)(
                dataFrom("""{ "name": "$name", "address": { "street": "Wayne Way" } }""")
            )

            result.shouldBeRight(Person(name))
        }
    }
})