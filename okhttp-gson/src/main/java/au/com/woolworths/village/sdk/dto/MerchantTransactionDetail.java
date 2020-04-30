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
import au.com.woolworths.village.sdk.dto.Basket;
import au.com.woolworths.village.sdk.dto.MerchantPayload;
import au.com.woolworths.village.sdk.dto.MerchantPaymentDetailAllOf;
import au.com.woolworths.village.sdk.dto.MerchantTransactionSummary;
import au.com.woolworths.village.sdk.dto.PosPayload;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;

/**
 * Detailed information for a single transaction
 */
@ApiModel(description = "Detailed information for a single transaction")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-30T15:44:28.835+10:00[Australia/Melbourne]")
public class MerchantTransactionDetail {
  public static final String SERIALIZED_NAME_TRANSACTION_ID = "transactionId";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_ID)
  private String transactionId;

  /**
   * The type of transaction: PAYMENT or REFUND
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    PAYMENT("PAYMENT"),
    
    REFUND("REFUND");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public static final String SERIALIZED_NAME_EXECUTION_TIME = "executionTime";
  @SerializedName(SERIALIZED_NAME_EXECUTION_TIME)
  private OffsetDateTime executionTime;

  /**
   * The current status of the transactions
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    PROCESSING("PROCESSING"),
    
    APPROVED("APPROVED"),
    
    REJECTED("REJECTED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status;

  public static final String SERIALIZED_NAME_REFUND_REASON = "refundReason";
  @SerializedName(SERIALIZED_NAME_REFUND_REASON)
  private String refundReason;

  public static final String SERIALIZED_NAME_PAYMENT_REQUEST_ID = "paymentRequestId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_REQUEST_ID)
  private String paymentRequestId;

  public static final String SERIALIZED_NAME_MERCHANT_REFERENCE_ID = "merchantReferenceId";
  @SerializedName(SERIALIZED_NAME_MERCHANT_REFERENCE_ID)
  private String merchantReferenceId;

  public static final String SERIALIZED_NAME_GROSS_AMOUNT = "grossAmount";
  @SerializedName(SERIALIZED_NAME_GROSS_AMOUNT)
  private BigDecimal grossAmount;

  public static final String SERIALIZED_NAME_WALLET_ID = "walletId";
  @SerializedName(SERIALIZED_NAME_WALLET_ID)
  private String walletId;

  public static final String SERIALIZED_NAME_BASKET = "basket";
  @SerializedName(SERIALIZED_NAME_BASKET)
  private Basket basket;

  public static final String SERIALIZED_NAME_POS_PAYLOAD = "posPayload";
  @SerializedName(SERIALIZED_NAME_POS_PAYLOAD)
  private PosPayload posPayload;

  public static final String SERIALIZED_NAME_MERCHANT_PAYLOAD = "merchantPayload";
  @SerializedName(SERIALIZED_NAME_MERCHANT_PAYLOAD)
  private MerchantPayload merchantPayload;


  public MerchantTransactionDetail transactionId(String transactionId) {
    
    this.transactionId = transactionId;
    return this;
  }

   /**
   * The ID of the transaction
   * @return transactionId
  **/
  @ApiModelProperty(example = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5", required = true, value = "The ID of the transaction")

  public String getTransactionId() {
    return transactionId;
  }


  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  public MerchantTransactionDetail type(TypeEnum type) {
    
    this.type = type;
    return this;
  }

   /**
   * The type of transaction: PAYMENT or REFUND
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The type of transaction: PAYMENT or REFUND")

  public TypeEnum getType() {
    return type;
  }


  public void setType(TypeEnum type) {
    this.type = type;
  }


  public MerchantTransactionDetail executionTime(OffsetDateTime executionTime) {
    
    this.executionTime = executionTime;
    return this;
  }

   /**
   * Date/time stamp of when the transaction occurred in ISO string format
   * @return executionTime
  **/
  @ApiModelProperty(example = "2017-11-06T19:38:09.890+11:00", required = true, value = "Date/time stamp of when the transaction occurred in ISO string format")

  public OffsetDateTime getExecutionTime() {
    return executionTime;
  }


  public void setExecutionTime(OffsetDateTime executionTime) {
    this.executionTime = executionTime;
  }


  public MerchantTransactionDetail status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * The current status of the transactions
   * @return status
  **/
  @ApiModelProperty(required = true, value = "The current status of the transactions")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public MerchantTransactionDetail refundReason(String refundReason) {
    
    this.refundReason = refundReason;
    return this;
  }

   /**
   * The reason provided for the refund.  Only provided for REFUND transactions
   * @return refundReason
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The reason provided for the refund.  Only provided for REFUND transactions")

  public String getRefundReason() {
    return refundReason;
  }


  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }


  public MerchantTransactionDetail paymentRequestId(String paymentRequestId) {
    
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


  public MerchantTransactionDetail merchantReferenceId(String merchantReferenceId) {
    
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


  public MerchantTransactionDetail grossAmount(BigDecimal grossAmount) {
    
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


  public MerchantTransactionDetail walletId(String walletId) {
    
    this.walletId = walletId;
    return this;
  }

   /**
   * The ID of the wallet associated with this transaction
   * @return walletId
  **/
  @ApiModelProperty(required = true, value = "The ID of the wallet associated with this transaction")

  public String getWalletId() {
    return walletId;
  }


  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }


  public MerchantTransactionDetail basket(Basket basket) {
    
    this.basket = basket;
    return this;
  }

   /**
   * Get basket
   * @return basket
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Basket getBasket() {
    return basket;
  }


  public void setBasket(Basket basket) {
    this.basket = basket;
  }


  public MerchantTransactionDetail posPayload(PosPayload posPayload) {
    
    this.posPayload = posPayload;
    return this;
  }

   /**
   * Get posPayload
   * @return posPayload
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PosPayload getPosPayload() {
    return posPayload;
  }


  public void setPosPayload(PosPayload posPayload) {
    this.posPayload = posPayload;
  }


  public MerchantTransactionDetail merchantPayload(MerchantPayload merchantPayload) {
    
    this.merchantPayload = merchantPayload;
    return this;
  }

   /**
   * Get merchantPayload
   * @return merchantPayload
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MerchantPayload getMerchantPayload() {
    return merchantPayload;
  }


  public void setMerchantPayload(MerchantPayload merchantPayload) {
    this.merchantPayload = merchantPayload;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchantTransactionDetail merchantTransactionDetail = (MerchantTransactionDetail) o;
    return Objects.equals(this.transactionId, merchantTransactionDetail.transactionId) &&
        Objects.equals(this.type, merchantTransactionDetail.type) &&
        Objects.equals(this.executionTime, merchantTransactionDetail.executionTime) &&
        Objects.equals(this.status, merchantTransactionDetail.status) &&
        Objects.equals(this.refundReason, merchantTransactionDetail.refundReason) &&
        Objects.equals(this.paymentRequestId, merchantTransactionDetail.paymentRequestId) &&
        Objects.equals(this.merchantReferenceId, merchantTransactionDetail.merchantReferenceId) &&
        Objects.equals(this.grossAmount, merchantTransactionDetail.grossAmount) &&
        Objects.equals(this.walletId, merchantTransactionDetail.walletId) &&
        Objects.equals(this.basket, merchantTransactionDetail.basket) &&
        Objects.equals(this.posPayload, merchantTransactionDetail.posPayload) &&
        Objects.equals(this.merchantPayload, merchantTransactionDetail.merchantPayload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, type, executionTime, status, refundReason, paymentRequestId, merchantReferenceId, grossAmount, walletId, basket, posPayload, merchantPayload);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantTransactionDetail {\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    executionTime: ").append(toIndentedString(executionTime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    refundReason: ").append(toIndentedString(refundReason)).append("\n");
    sb.append("    paymentRequestId: ").append(toIndentedString(paymentRequestId)).append("\n");
    sb.append("    merchantReferenceId: ").append(toIndentedString(merchantReferenceId)).append("\n");
    sb.append("    grossAmount: ").append(toIndentedString(grossAmount)).append("\n");
    sb.append("    walletId: ").append(toIndentedString(walletId)).append("\n");
    sb.append("    basket: ").append(toIndentedString(basket)).append("\n");
    sb.append("    posPayload: ").append(toIndentedString(posPayload)).append("\n");
    sb.append("    merchantPayload: ").append(toIndentedString(merchantPayload)).append("\n");
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

