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
 * An instrument used for this transaction
 */
@ApiModel(description = "An instrument used for this transaction")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-01T10:39:59.330+10:00[Australia/Melbourne]")
public class CustomerTransactionSummaryAllOfInstruments {
  public static final String SERIALIZED_NAME_PAYMENT_INSTRUMENT_ID = "paymentInstrumentId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_INSTRUMENT_ID)
  private String paymentInstrumentId;

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  @SerializedName(SERIALIZED_NAME_AMOUNT)
  private BigDecimal amount;

  public static final String SERIALIZED_NAME_PAYMENT_TRANSACTION_REF = "paymentTransactionRef";
  @SerializedName(SERIALIZED_NAME_PAYMENT_TRANSACTION_REF)
  private String paymentTransactionRef;


  public CustomerTransactionSummaryAllOfInstruments paymentInstrumentId(String paymentInstrumentId) {
    
    this.paymentInstrumentId = paymentInstrumentId;
    return this;
  }

   /**
   * The ID of the payment instrument
   * @return paymentInstrumentId
  **/
  @ApiModelProperty(required = true, value = "The ID of the payment instrument")

  public String getPaymentInstrumentId() {
    return paymentInstrumentId;
  }


  public void setPaymentInstrumentId(String paymentInstrumentId) {
    this.paymentInstrumentId = paymentInstrumentId;
  }


  public CustomerTransactionSummaryAllOfInstruments amount(BigDecimal amount) {
    
    this.amount = amount;
    return this;
  }

   /**
   * The amount charged against or refunded to this instrument
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "The amount charged against or refunded to this instrument")

  public BigDecimal getAmount() {
    return amount;
  }


  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  public CustomerTransactionSummaryAllOfInstruments paymentTransactionRef(String paymentTransactionRef) {
    
    this.paymentTransactionRef = paymentTransactionRef;
    return this;
  }

   /**
   * The reference for the payment
   * @return paymentTransactionRef
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The reference for the payment")

  public String getPaymentTransactionRef() {
    return paymentTransactionRef;
  }


  public void setPaymentTransactionRef(String paymentTransactionRef) {
    this.paymentTransactionRef = paymentTransactionRef;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerTransactionSummaryAllOfInstruments customerTransactionSummaryAllOfInstruments = (CustomerTransactionSummaryAllOfInstruments) o;
    return Objects.equals(this.paymentInstrumentId, customerTransactionSummaryAllOfInstruments.paymentInstrumentId) &&
        Objects.equals(this.amount, customerTransactionSummaryAllOfInstruments.amount) &&
        Objects.equals(this.paymentTransactionRef, customerTransactionSummaryAllOfInstruments.paymentTransactionRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentInstrumentId, amount, paymentTransactionRef);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerTransactionSummaryAllOfInstruments {\n");
    sb.append("    paymentInstrumentId: ").append(toIndentedString(paymentInstrumentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    paymentTransactionRef: ").append(toIndentedString(paymentTransactionRef)).append("\n");
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

