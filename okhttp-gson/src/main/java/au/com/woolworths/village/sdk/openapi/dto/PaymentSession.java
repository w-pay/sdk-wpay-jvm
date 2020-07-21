/*
 * Village Wallet
 * APIs for Village Wallet
 *
 * The version of the OpenAPI document: 0.0.5
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package au.com.woolworths.village.sdk.openapi.dto;

import com.google.gson.annotations.SerializedName;

import org.threeten.bp.OffsetDateTime;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Mandatory data object containing response
 */
@ApiModel(description = "Mandatory data object containing response")

public class PaymentSession implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_PAYMENT_SESSION_ID = "paymentSessionId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_SESSION_ID)
  private String paymentSessionId;

  public static final String SERIALIZED_NAME_MERCHANT_ID = "merchantId";
  @SerializedName(SERIALIZED_NAME_MERCHANT_ID)
  private String merchantId;

  public static final String SERIALIZED_NAME_WALLET_ID = "walletId";
  @SerializedName(SERIALIZED_NAME_WALLET_ID)
  private String walletId;

  public static final String SERIALIZED_NAME_EXPIRY_TIME = "expiryTime";
  @SerializedName(SERIALIZED_NAME_EXPIRY_TIME)
  private OffsetDateTime expiryTime;

  public static final String SERIALIZED_NAME_LOCATION = "location";
  @SerializedName(SERIALIZED_NAME_LOCATION)
  private String location;

  public static final String SERIALIZED_NAME_ADDITIONAL_INFO = "additionalInfo";
  @SerializedName(SERIALIZED_NAME_ADDITIONAL_INFO)
  private DynamicPayload additionalInfo;


  public PaymentSession paymentSessionId(String paymentSessionId) {
    
    this.paymentSessionId = paymentSessionId;
    return this;
  }

   /**
   * The ID of the payment session
   * @return paymentSessionId
  **/
  @ApiModelProperty(required = true, value = "The ID of the payment session")

  public String getPaymentSessionId() {
    return paymentSessionId;
  }


  public void setPaymentSessionId(String paymentSessionId) {
    this.paymentSessionId = paymentSessionId;
  }


  public PaymentSession merchantId(String merchantId) {
    
    this.merchantId = merchantId;
    return this;
  }

   /**
   * The ID of the merchant initiating the payment session
   * @return merchantId
  **/
  @ApiModelProperty(required = true, value = "The ID of the merchant initiating the payment session")

  public String getMerchantId() {
    return merchantId;
  }


  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }


  public PaymentSession walletId(String walletId) {
    
    this.walletId = walletId;
    return this;
  }

   /**
   * The ID of the customers wallet
   * @return walletId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ID of the customers wallet")

  public String getWalletId() {
    return walletId;
  }


  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }


  public PaymentSession expiryTime(OffsetDateTime expiryTime) {
    
    this.expiryTime = expiryTime;
    return this;
  }

   /**
   * The ISO date/time for when the payment session will expire and become unusable
   * @return expiryTime
  **/
  @ApiModelProperty(example = "2017-11-06T19:38:09.890+11:00", required = true, value = "The ISO date/time for when the payment session will expire and become unusable")

  public OffsetDateTime getExpiryTime() {
    return expiryTime;
  }


  public void setExpiryTime(OffsetDateTime expiryTime) {
    this.expiryTime = expiryTime;
  }


  public PaymentSession location(String location) {
    
    this.location = location;
    return this;
  }

   /**
   * The location of the payment session (Used to group payment sessions)
   * @return location
  **/
  @ApiModelProperty(required = true, value = "The location of the payment session (Used to group payment sessions)")

  public String getLocation() {
    return location;
  }


  public void setLocation(String location) {
    this.location = location;
  }


  public PaymentSession additionalInfo(DynamicPayload additionalInfo) {
    
    this.additionalInfo = additionalInfo;
    return this;
  }

   /**
   * Get additionalInfo
   * @return additionalInfo
  **/
  @ApiModelProperty(required = true, value = "")

  public DynamicPayload getAdditionalInfo() {
    return additionalInfo;
  }


  public void setAdditionalInfo(DynamicPayload additionalInfo) {
    this.additionalInfo = additionalInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentSession paymentSession = (PaymentSession) o;
    return Objects.equals(this.paymentSessionId, paymentSession.paymentSessionId) &&
        Objects.equals(this.merchantId, paymentSession.merchantId) &&
        Objects.equals(this.walletId, paymentSession.walletId) &&
        Objects.equals(this.expiryTime, paymentSession.expiryTime) &&
        Objects.equals(this.location, paymentSession.location) &&
        Objects.equals(this.additionalInfo, paymentSession.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentSessionId, merchantId, walletId, expiryTime, location, additionalInfo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentSession {\n");
    sb.append("    paymentSessionId: ").append(toIndentedString(paymentSessionId)).append("\n");
    sb.append("    merchantId: ").append(toIndentedString(merchantId)).append("\n");
    sb.append("    walletId: ").append(toIndentedString(walletId)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
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
