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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-30T15:44:28.835+10:00[Australia/Melbourne]")
public class CustomerPaymentsPaymentRequestIdDataSecondaryInstruments {
  public static final String SERIALIZED_NAME_INSTRUMENT_ID = "instrumentId";
  @SerializedName(SERIALIZED_NAME_INSTRUMENT_ID)
  private String instrumentId;

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  @SerializedName(SERIALIZED_NAME_AMOUNT)
  private BigDecimal amount;


  public CustomerPaymentsPaymentRequestIdDataSecondaryInstruments instrumentId(String instrumentId) {
    
    this.instrumentId = instrumentId;
    return this;
  }

   /**
   * The ID of the payment instrument
   * @return instrumentId
  **/
  @ApiModelProperty(required = true, value = "The ID of the payment instrument")

  public String getInstrumentId() {
    return instrumentId;
  }


  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }


  public CustomerPaymentsPaymentRequestIdDataSecondaryInstruments amount(BigDecimal amount) {
    
    this.amount = amount;
    return this;
  }

   /**
   * The amount to charged against this instrument.  Must be greater than zero
   * minimum: 0
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "The amount to charged against this instrument.  Must be greater than zero")

  public BigDecimal getAmount() {
    return amount;
  }


  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerPaymentsPaymentRequestIdDataSecondaryInstruments customerPaymentsPaymentRequestIdDataSecondaryInstruments = (CustomerPaymentsPaymentRequestIdDataSecondaryInstruments) o;
    return Objects.equals(this.instrumentId, customerPaymentsPaymentRequestIdDataSecondaryInstruments.instrumentId) &&
        Objects.equals(this.amount, customerPaymentsPaymentRequestIdDataSecondaryInstruments.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instrumentId, amount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerPaymentsPaymentRequestIdDataSecondaryInstruments {\n");
    sb.append("    instrumentId: ").append(toIndentedString(instrumentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

