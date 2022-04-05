package au.com.wpay.sdk.model

import au.com.wpay.sdk.data.healthCheckDTO
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class HealthCheckTest: DescribeSpec({
    describe("Health Check Transformer") {
        describe("from DTO") {
            it("should convert DTO") {
                val dto = healthCheckDTO()

                val result: HealthCheck = fromDTO(dto)
                result.result.shouldBe(HealthCheck.Status.SUCCESS)
            }

            it("should convert lowercase status") {
                val dto = healthCheckDTO("success")

                val result: HealthCheck = fromDTO(dto)
                result.result.shouldBe(HealthCheck.Status.SUCCESS)
            }
        }
    }
})
