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
import au.com.woolworths.village.sdk.dto.CommonTransactionSummary;
import au.com.woolworths.village.sdk.dto.CustomerTransactionSummaryAllOf;
import au.com.woolworths.village.sdk.dto.CustomerTransactionSummaryAllOfInstruments;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * Summary information of the resulting transaction
 */
@ApiModel(description = "Summary information of the resulting transaction")

public class CustomerTransactionSummary extends CommonTransactionSummary {
  public static final String SERIALIZED_NAME_MERCHANT_ID = "merchantId";
  @SerializedName(SERIALIZED_NAME_MERCHANT_ID)
  private String merchantId;

  public static final String SERIALIZED_NAME_INSTRUMENTS = "instruments";
  @SerializedName(SERIALIZED_NAME_INSTRUMENTS)
  private List<CustomerTransactionSummaryAllOfInstruments> instruments = new ArrayList<CustomerTransactionSummaryAllOfInstruments>();


  public CustomerTransactionSummary merchantId(String merchantId) {
    
    this.merchantId = merchantId;
    return this;
  }

   /**
   * The ID of the merchant associated with this transaction
   * @return merchantId
  **/
  @ApiModelProperty(example = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5", required = true, value = "The ID of the merchant associated with this transaction")

  public String getMerchantId() {
    return merchantId;
  }


  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }


  public CustomerTransactionSummary instruments(List<CustomerTransactionSummaryAllOfInstruments> instruments) {
    
    this.instruments = instruments;
    return this;
  }

  public CustomerTransactionSummary addInstrumentsItem(CustomerTransactionSummaryAllOfInstruments instrumentsItem) {
    this.instruments.add(instrumentsItem);
    return this;
  }

   /**
   * The instruments used to make the payment.  For refunds and cash back amounts will be negative
   * @return instruments
  **/
  @ApiModelProperty(required = true, value = "The instruments used to make the payment.  For refunds and cash back amounts will be negative")

  public List<CustomerTransactionSummaryAllOfInstruments> getInstruments() {
    return instruments;
  }


  public void setInstruments(List<CustomerTransactionSummaryAllOfInstruments> instruments) {
    this.instruments = instruments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerTransactionSummary customerTransactionSummary = (CustomerTransactionSummary) o;
    return Objects.equals(this.merchantId, customerTransactionSummary.merchantId) &&
        Objects.equals(this.instruments, customerTransactionSummary.instruments) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantId, instruments, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerTransactionSummary {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    merchantId: ").append(toIndentedString(merchantId)).append("\n");
    sb.append("    instruments: ").append(toIndentedString(instruments)).append("\n");
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

