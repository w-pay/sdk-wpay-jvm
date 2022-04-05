package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.Basket
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun aNewBasket(): Basket =
    Basket(
        items = listOf(
            Basket.Item(
                label = "Item 1",
                description = "This is item 1",
                quantity = BigDecimal(3.0),
                unitPrice = BigDecimal(2.1),
                unitMeasure = "EACH",
                totalPrice = BigDecimal(6.3),
                tags = mapOf(
                    "property1" to "string",
                    "property2" to "string"
                )
            )
        )
    )

fun basketDTO(): JsonObject =
    JsonObject(mapOf(
        "items" to JsonArray(listOf(
            JsonObject(mapOf(
                "label" to JsonPrimitive("Item 1"),
                "description" to JsonPrimitive("This is item 1"),
                "quantity" to JsonPrimitive(3.0),
                "unitPrice" to JsonPrimitive(2.1),
                "unitMeasure" to JsonPrimitive("EACH"),
                "totalPrice" to JsonPrimitive(6.3),
                "tags" to JsonObject(mapOf(
                    "property1" to JsonPrimitive("string"),
                    "property2" to JsonPrimitive("string")
                ))
            ))
        )
    )))
