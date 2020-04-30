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
 * Detail of a QR code
 */
@ApiModel(description = "Detail of a QR code")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-30T10:56:50.564+10:00[Australia/Melbourne]")
public class Qr {
  public static final String SERIALIZED_NAME_QR_ID = "qrId";
  @SerializedName(SERIALIZED_NAME_QR_ID)
  private String qrId;

  public static final String SERIALIZED_NAME_REFERENCE_ID = "referenceId";
  @SerializedName(SERIALIZED_NAME_REFERENCE_ID)
  private String referenceId;

  /**
   * The type of ID held in referenceId
   */
  @JsonAdapter(ReferenceTypeEnum.Adapter.class)
  public enum ReferenceTypeEnum {
    REQUEST("PAYMENT_REQUEST"),
    
    POINT("PAYMENT_POINT");

    private String value;

    ReferenceTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ReferenceTypeEnum fromValue(String value) {
      for (ReferenceTypeEnum b : ReferenceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<ReferenceTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ReferenceTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ReferenceTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return ReferenceTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_REFERENCE_TYPE = "referenceType";
  @SerializedName(SERIALIZED_NAME_REFERENCE_TYPE)
  private ReferenceTypeEnum referenceType;

  public static final String SERIALIZED_NAME_CONTENT = "content";
  @SerializedName(SERIALIZED_NAME_CONTENT)
  private String content;

  public static final String SERIALIZED_NAME_IMAGE = "image";
  @SerializedName(SERIALIZED_NAME_IMAGE)
  private String image;

  public static final String SERIALIZED_NAME_EXPIRY_TIME = "expiryTime";
  @SerializedName(SERIALIZED_NAME_EXPIRY_TIME)
  private String expiryTime;


  public Qr qrId(String qrId) {
    
    this.qrId = qrId;
    return this;
  }

   /**
   * The ID of the QR code
   * @return qrId
  **/
  @ApiModelProperty(required = true, value = "The ID of the QR code")

  public String getQrId() {
    return qrId;
  }


  public void setQrId(String qrId) {
    this.qrId = qrId;
  }


  public Qr referenceId(String referenceId) {
    
    this.referenceId = referenceId;
    return this;
  }

   /**
   * The ID of the payment request linked to this QR code
   * @return referenceId
  **/
  @ApiModelProperty(required = true, value = "The ID of the payment request linked to this QR code")

  public String getReferenceId() {
    return referenceId;
  }


  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }


  public Qr referenceType(ReferenceTypeEnum referenceType) {
    
    this.referenceType = referenceType;
    return this;
  }

   /**
   * The type of ID held in referenceId
   * @return referenceType
  **/
  @ApiModelProperty(example = "PAYMENT_REQUEST", required = true, value = "The type of ID held in referenceId")

  public ReferenceTypeEnum getReferenceType() {
    return referenceType;
  }


  public void setReferenceType(ReferenceTypeEnum referenceType) {
    this.referenceType = referenceType;
  }


  public Qr content(String content) {
    
    this.content = content;
    return this;
  }

   /**
   * The text content for the QR code
   * @return content
  **/
  @ApiModelProperty(required = true, value = "The text content for the QR code")

  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public Qr image(String image) {
    
    this.image = image;
    return this;
  }

   /**
   * Base64 encoded PNG of the QR Code
   * @return image
  **/
  @ApiModelProperty(required = true, value = "Base64 encoded PNG of the QR Code")

  public String getImage() {
    return image;
  }


  public void setImage(String image) {
    this.image = image;
  }


  public Qr expiryTime(String expiryTime) {
    
    this.expiryTime = expiryTime;
    return this;
  }

   /**
   * ISO date/time string indicating when the QR code will expire and become ineffective.  If absent then the QR code will not expire until it is deleted
   * @return expiryTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ISO date/time string indicating when the QR code will expire and become ineffective.  If absent then the QR code will not expire until it is deleted")

  public String getExpiryTime() {
    return expiryTime;
  }


  public void setExpiryTime(String expiryTime) {
    this.expiryTime = expiryTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Qr qr = (Qr) o;
    return Objects.equals(this.qrId, qr.qrId) &&
        Objects.equals(this.referenceId, qr.referenceId) &&
        Objects.equals(this.referenceType, qr.referenceType) &&
        Objects.equals(this.content, qr.content) &&
        Objects.equals(this.image, qr.image) &&
        Objects.equals(this.expiryTime, qr.expiryTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(qrId, referenceId, referenceType, content, image, expiryTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Qr {\n");
    sb.append("    qrId: ").append(toIndentedString(qrId)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
    sb.append("    referenceType: ").append(toIndentedString(referenceType)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
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
