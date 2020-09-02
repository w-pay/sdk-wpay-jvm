package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.CredentialsStore
import au.com.woolworths.village.sdk.auth.StoringApiAuthenticator
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class StoringApiAuthenticatorTest {
    @Mock
    private val apiAuthenticator: ApiAuthenticator<String>? = null

    @Mock
    private val credentialsStore: CredentialsStore<String>? = null

    @InjectMocks
    private lateinit var storingApiAuthenticator: StoringApiAuthenticator<String>

    @Test
    fun shouldStoreCredentialsWhenSuccessful() {
        val credentials = "credentials"
        `when`(apiAuthenticator?.authenticate()).thenReturn(ApiResult.Success(credentials))

        val result: ApiResult.Success<String> = storingApiAuthenticator.authenticate() as ApiResult.Success<String>

        assertThat(result.value, equalTo(credentials))

        verify(credentialsStore)?.storeCredentials(credentials)
    }

    @Test
    fun shouldNotStoreCredentialsWhenError() {
        val error = ApiException(500, emptyMap(), "")
        `when`(apiAuthenticator?.authenticate()).thenReturn(ApiResult.Error(error))

        val result: ApiResult.Error = storingApiAuthenticator.authenticate() as ApiResult.Error

        assertThat(result.e, equalTo(error))
        verify(credentialsStore, never())?.storeCredentials(anyString())
    }
}