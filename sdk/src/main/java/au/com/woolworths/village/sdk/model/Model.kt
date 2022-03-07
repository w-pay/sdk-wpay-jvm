package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Because kotlinx-serialization requires constructor properties it is very difficult to achieve
 * polymorphic reuse even thought the API spec does have some inheritance in it.
 *
 * To overcome this problem and to have the compiler ensure that data classes have the required
 * properties, "Type" interfaces can be used to mirror API spec types, with the final data
 * classes implementing the interface and thus having to provide the needed constructor properties.
 *
 * @see https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/basic-serialization.md#constructor-properties-requirement
 */
interface ModelType : Serializable