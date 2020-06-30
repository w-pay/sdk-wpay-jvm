/*
 * Village Wallet
 * APIs for Village Wallet
 *
 * The version of the OpenAPI document: 0.0.4
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package au.com.woolworths.village.sdk.openapi.dto;

import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.io.Serializable;

/**
 * Mandatory data object containing response
 */
@ApiModel(description = "Mandatory data object containing response")

public class HealthCheckResultData implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Health check result
   */
  @JsonAdapter(HealthCheckEnum.Adapter.class)
  public enum HealthCheckEnum {
    SUCCESS("success");

    private String value;

    HealthCheckEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static HealthCheckEnum fromValue(String value) {
      for (HealthCheckEnum b : HealthCheckEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<HealthCheckEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final HealthCheckEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public HealthCheckEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return HealthCheckEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_HEALTH_CHECK = "healthCheck";
  @SerializedName(SERIALIZED_NAME_HEALTH_CHECK)
  private HealthCheckEnum healthCheck;


  public HealthCheckResultData healthCheck(HealthCheckEnum healthCheck) {
    
    this.healthCheck = healthCheck;
    return this;
  }

   /**
   * Health check result
   * @return healthCheck
  **/
  @ApiModelProperty(example = "success", required = true, value = "Health check result")

  public HealthCheckEnum getHealthCheck() {
    return healthCheck;
  }


  public void setHealthCheck(HealthCheckEnum healthCheck) {
    this.healthCheck = healthCheck;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HealthCheckResultData healthCheckResultData = (HealthCheckResultData) o;
    return Objects.equals(this.healthCheck, healthCheckResultData.healthCheck);
  }

  @Override
  public int hashCode() {
    return Objects.hash(healthCheck);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HealthCheckResultData {\n");
    sb.append("    healthCheck: ").append(toIndentedString(healthCheck)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
