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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * DynamicPayload
 */

public class DynamicPayload implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_SCHEMA_ID = "schemaId";
  @SerializedName(SERIALIZED_NAME_SCHEMA_ID)
  private String schemaId;

  public static final String SERIALIZED_NAME_PAYLOAD = "payload";
  @SerializedName(SERIALIZED_NAME_PAYLOAD)
  private Map<String, Object> payload = null;


  public DynamicPayload schemaId(String schemaId) {
    
    this.schemaId = schemaId;
    return this;
  }

   /**
   * The ID of the previously configured schema that will be used to validate the contents of the payload
   * @return schemaId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ID of the previously configured schema that will be used to validate the contents of the payload")

  public String getSchemaId() {
    return schemaId;
  }


  public void setSchemaId(String schemaId) {
    this.schemaId = schemaId;
  }


  public DynamicPayload payload(Map<String, Object> payload) {
    
    this.payload = payload;
    return this;
  }

  public DynamicPayload putPayloadItem(String key, Object payloadItem) {
    if (this.payload == null) {
      this.payload = new HashMap<String, Object>();
    }
    this.payload.put(key, payloadItem);
    return this;
  }

   /**
   * The payload aligned to the supplied schema
   * @return payload
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The payload aligned to the supplied schema")

  public Map<String, Object> getPayload() {
    return payload;
  }


  public void setPayload(Map<String, Object> payload) {
    this.payload = payload;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DynamicPayload dynamicPayload = (DynamicPayload) o;
    return Objects.equals(this.schemaId, dynamicPayload.schemaId) &&
        Objects.equals(this.payload, dynamicPayload.payload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemaId, payload);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DynamicPayload {\n");
    sb.append("    schemaId: ").append(toIndentedString(schemaId)).append("\n");
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
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
