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
import java.math.BigDecimal;

/**
 * CommonPaymentBase
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-01T10:39:59.330+10:00[Australia/Melbourne]")
public class CommonPaymentBase {
  public static final String SERIALIZED_NAME_PAYMENT_REQUEST_ID = "paymentRequestId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_REQUEST_ID)
  private String paymentRequestId;

  public static final String SERIALIZED_NAME_MERCHANT_REFERENCE_ID = "merchantReferenceId";
  @SerializedName(SERIALIZED_NAME_MERCHANT_REFERENCE_ID)
  private String merchantReferenceId;

  public static final String SERIALIZED_NAME_GROSS_AMOUNT = "grossAmount";
  @SerializedName(SERIALIZED_NAME_GROSS_AMOUNT)
  private BigDecimal grossAmount;


  public CommonPaymentBase paymentRequestId(String paymentRequestId) {
    
    this.paymentRequestId = paymentRequestId;
    return this;
  }

   /**
   * The ID of this payment request
   * @return paymentRequestId
  **/
  @ApiModelProperty(example = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5", required = true, value = "The ID of this payment request")

  public String getPaymentRequestId() {
    return paymentRequestId;
  }


  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }


  public CommonPaymentBase merchantReferenceId(String merchantReferenceId) {
    
    this.merchantReferenceId = merchantReferenceId;
    return this;
  }

   /**
   * The unique reference for the payment as defined by the Merchant
   * @return merchantReferenceId
  **/
  @ApiModelProperty(example = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5", required = true, value = "The unique reference for the payment as defined by the Merchant")

  public String getMerchantReferenceId() {
    return merchantReferenceId;
  }


  public void setMerchantReferenceId(String merchantReferenceId) {
    this.merchantReferenceId = merchantReferenceId;
  }


  public CommonPaymentBase grossAmount(BigDecimal grossAmount) {
    
    this.grossAmount = grossAmount;
    return this;
  }

   /**
   * The gross amount to be paid.  Must be positive except for refunds
   * @return grossAmount
  **/
  @ApiModelProperty(required = true, value = "The gross amount to be paid.  Must be positive except for refunds")

  public BigDecimal getGrossAmount() {
    return grossAmount;
  }


  public void setGrossAmount(BigDecimal grossAmount) {
    this.grossAmount = grossAmount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommonPaymentBase commonPaymentBase = (CommonPaymentBase) o;
    return Objects.equals(this.paymentRequestId, commonPaymentBase.paymentRequestId) &&
        Objects.equals(this.merchantReferenceId, commonPaymentBase.merchantReferenceId) &&
        Objects.equals(this.grossAmount, commonPaymentBase.grossAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentRequestId, merchantReferenceId, grossAmount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonPaymentBase {\n");
    sb.append("    paymentRequestId: ").append(toIndentedString(paymentRequestId)).append("\n");
    sb.append("    merchantReferenceId: ").append(toIndentedString(merchantReferenceId)).append("\n");
    sb.append("    grossAmount: ").append(toIndentedString(grossAmount)).append("\n");
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

