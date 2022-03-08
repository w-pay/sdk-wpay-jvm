package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.QRCode
import au.com.woolworths.village.sdk.model.QRCodePaymentReferenceType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.threeten.bp.OffsetDateTime
import java.util.*

fun aQRCode(): QRCode =
    QRCode(
        qrId = UUID.randomUUID().toString(),
        referenceId = UUID.randomUUID().toString(),
        referenceType = QRCodePaymentReferenceType.PAYMENT_REQUEST,
        content = "qr code contents",
        image = "pretend this is base64 encoded",
        expiryTime = OffsetDateTime.parse("2022-03-07T05:11:35.315Z")
    )

fun qrCodeDTO(): JsonObject =
    JsonObject(mapOf(
        "qrId" to JsonPrimitive(UUID.randomUUID().toString()),
        "referenceId" to JsonPrimitive(UUID.randomUUID().toString()),
        "referenceType" to JsonPrimitive(
            QRCodePaymentReferenceType.PAYMENT_REQUEST.toString().lowercase()
        ),
        "content" to JsonPrimitive("qr code contents"),
        "image" to JsonPrimitive("pretend this is base64 encoded"),
        "expiryTime" to JsonPrimitive("2022-03-07T05:11:35.315Z")
    ))