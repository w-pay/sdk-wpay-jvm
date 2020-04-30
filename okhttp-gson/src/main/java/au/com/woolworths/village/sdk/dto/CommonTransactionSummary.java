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
import au.com.woolworths.village.sdk.dto.CommonPaymentBase;
import au.com.woolworths.village.sdk.dto.CommonTransactionSummaryAllOf;
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
 * CommonTransactionSummary
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-30T15:44:28.835+10:00[Australia/Melbourne]")
public class CommonTransactionSummary extends CommonPaymentBase {
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


  public CommonTransactionSummary transactionId(String transactionId) {
    
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


  public CommonTransactionSummary type(TypeEnum type) {
    
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


  public CommonTransactionSummary executionTime(OffsetDateTime executionTime) {
    
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


  public CommonTransactionSummary status(StatusEnum status) {
    
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


  public CommonTransactionSummary refundReason(String refundReason) {
    
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommonTransactionSummary commonTransactionSummary = (CommonTransactionSummary) o;
    return Objects.equals(this.transactionId, commonTransactionSummary.transactionId) &&
        Objects.equals(this.type, commonTransactionSummary.type) &&
        Objects.equals(this.executionTime, commonTransactionSummary.executionTime) &&
        Objects.equals(this.status, commonTransactionSummary.status) &&
        Objects.equals(this.refundReason, commonTransactionSummary.refundReason) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, type, executionTime, status, refundReason, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommonTransactionSummary {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    executionTime: ").append(toIndentedString(executionTime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    refundReason: ").append(toIndentedString(refundReason)).append("\n");
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

