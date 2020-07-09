package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.MerchantPayload
import au.com.woolworths.village.sdk.model.PosPayload
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun isPosPayload(): PosPayloadMatcher {
    return PosPayloadMatcher()
}

class PosPayloadMatcher: TypeSafeMatcher<PosPayload>() {
    override fun matchesSafely(item: PosPayload): Boolean {
        assertThat(item.schemaId(), not(blankOrNullString()))
        assertThat(item.payload(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Pos payload details")
    }

}

fun isMerchantPayload(): MerchantPayloadMatcher {
    return MerchantPayloadMatcher()
}

class MerchantPayloadMatcher: TypeSafeMatcher<MerchantPayload>() {
    override fun matchesSafely(item: MerchantPayload): Boolean {
        assertThat(item.schemaId(), not(blankOrNullString()))
        assertThat(item.payload(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant payload details")
    }
}