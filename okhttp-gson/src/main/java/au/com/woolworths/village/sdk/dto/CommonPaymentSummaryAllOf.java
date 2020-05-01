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


package au.com.woolworths.village.sdk.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * CommonPaymentSummaryAllOf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-01T10:39:59.330+10:00[Australia/Melbourne]")
public class CommonPaymentSummaryAllOf {
  public static final String SERIALIZED_NAME_CREATED_TIME = "createdTime";
  @SerializedName(SERIALIZED_NAME_CREATED_TIME)
  private String createdTime;


  public CommonPaymentSummaryAllOf createdTime(String createdTime) {
    
    this.createdTime = createdTime;
    return this;
  }

   /**
   * The ISO date/time that the payment request was created
   * @return createdTime
  **/
  @ApiModelProperty(required = true, value = "The ISO date/time that the payment request was created")

  public String getCreatedTime() {
    return createdTime;
  }


  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommonPaymentSummaryAllOf commonPaymentSummaryAllOf = (CommonPaymentSummaryAllOf) o;
    return Objects.equals(this.createdTime, commonPaymentSummaryAllOf.createdTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonPaymentSummaryAllOf {\n");
    sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
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

