package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.QRCode
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun aQrCode(): Matcher<QRCode> {
    return QRCodeMatcher()
}

fun isAQrCode(): Matcher<QRCode> = aQrCode()

class QRCodeMatcher: TypeSafeMatcher<QRCode>() {
    override fun matchesSafely(item: QRCode): Boolean {
        assertThat(item.qrId(), not(blankOrNullString()))
        assertThat(item.referenceId(), not(blankOrNullString()))
        assertThat(item.referenceType(), not(nullValue()))
        assertThat(item.content(), not(blankOrNullString()))
        assertThat(item.image(), not(blankOrNullString()))
        assertThat(item.expiryTime(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A QR code")
    }
}