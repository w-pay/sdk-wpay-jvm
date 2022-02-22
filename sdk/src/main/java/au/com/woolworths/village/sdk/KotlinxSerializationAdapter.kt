package au.com.woolworths.village.sdk

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.GenericTypeUnmarshaller
import au.com.redcrew.apisdkcreator.httpclient.SdkError
import au.com.redcrew.apisdkcreator.httpclient.UNMARSHALLING_ERROR_TYPE
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredDataToGenericTypeUnmarshaller
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

fun kotlinxSerialisationUnmarshaller(): GenericTypeUnmarshaller =
    object: UnstructuredDataToGenericTypeUnmarshaller() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> unmarshallString(cls: KClass<T>, data: String): Either<SdkError, T> {
            val parser = Json {
                ignoreUnknownKeys = true
            }

            return try {
                val deserializer: KSerializer<T> = parser.serializersModule.serializer(cls.createType()) as KSerializer<T>

                parser.decodeFromString(deserializer, data).right()
            }
            catch (e: Exception) {
                SdkError(UNMARSHALLING_ERROR_TYPE, e.message!!, e).left()
            }
        }
    }