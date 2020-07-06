package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.Qr
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun isQrCode(): QrCodeMatcher {
    return QrCodeMatcher()
}

class QrCodeMatcher: TypeSafeMatcher<Qr>() {
    override fun matchesSafely(item: Qr): Boolean {
        assertThat(item, not(nullValue()))

        assertThat(item.qrId, not(blankOrNullString()))
        assertThat(item.referenceId, not(blankOrNullString()))
        assertThat(item.referenceType, not(nullValue()))
        assertThat(item.content, not(blankOrNullString()))
        assertThat(item.image, not(blankOrNullString()))
        assertThat(item.expiryTime, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A QR code")
    }
}