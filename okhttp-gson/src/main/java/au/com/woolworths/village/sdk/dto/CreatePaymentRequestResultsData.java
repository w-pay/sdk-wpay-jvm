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
import au.com.woolworths.village.sdk.dto.Qr;
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

public class CreatePaymentRequestResultsData implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_PAYMENT_REQUEST_ID = "paymentRequestId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_REQUEST_ID)
  private String paymentRequestId;

  public static final String SERIALIZED_NAME_QR = "qr";
  @SerializedName(SERIALIZED_NAME_QR)
  private Qr qr;


  public CreatePaymentRequestResultsData paymentRequestId(String paymentRequestId) {
    
    this.paymentRequestId = paymentRequestId;
    return this;
  }

   /**
   * The ID of the new payment request
   * @return paymentRequestId
  **/
  @ApiModelProperty(required = true, value = "The ID of the new payment request")

  public String getPaymentRequestId() {
    return paymentRequestId;
  }


  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }


  public CreatePaymentRequestResultsData qr(Qr qr) {
    
    this.qr = qr;
    return this;
  }

   /**
   * Get qr
   * @return qr
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Qr getQr() {
    return qr;
  }


  public void setQr(Qr qr) {
    this.qr = qr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePaymentRequestResultsData createPaymentRequestResultsData = (CreatePaymentRequestResultsData) o;
    return Objects.equals(this.paymentRequestId, createPaymentRequestResultsData.paymentRequestId) &&
        Objects.equals(this.qr, createPaymentRequestResultsData.qr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentRequestId, qr);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePaymentRequestResultsData {\n");
    sb.append("    paymentRequestId: ").append(toIndentedString(paymentRequestId)).append("\n");
    sb.append("    qr: ").append(toIndentedString(qr)).append("\n");
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

