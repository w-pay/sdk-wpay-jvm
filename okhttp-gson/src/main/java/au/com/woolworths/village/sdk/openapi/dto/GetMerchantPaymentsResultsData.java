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

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Mandatory data object containing response
 */
@ApiModel(description = "Mandatory data object containing response")

public class GetMerchantPaymentsResultsData implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_PAYMENTS = "payments";
  @SerializedName(SERIALIZED_NAME_PAYMENTS)
  private List<MerchantPaymentSummary> payments = new ArrayList<MerchantPaymentSummary>();


  public GetMerchantPaymentsResultsData payments(List<MerchantPaymentSummary> payments) {
    
    this.payments = payments;
    return this;
  }

  public GetMerchantPaymentsResultsData addPaymentsItem(MerchantPaymentSummary paymentsItem) {
    this.payments.add(paymentsItem);
    return this;
  }

   /**
   * The resulting list of payments.  If no results match the request then an empty array will be returned
   * @return payments
  **/
  @ApiModelProperty(required = true, value = "The resulting list of payments.  If no results match the request then an empty array will be returned")

  public List<MerchantPaymentSummary> getPayments() {
    return payments;
  }


  public void setPayments(List<MerchantPaymentSummary> payments) {
    this.payments = payments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetMerchantPaymentsResultsData getMerchantPaymentsResultsData = (GetMerchantPaymentsResultsData) o;
    return Objects.equals(this.payments, getMerchantPaymentsResultsData.payments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payments);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMerchantPaymentsResultsData {\n");
    sb.append("    payments: ").append(toIndentedString(payments)).append("\n");
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

