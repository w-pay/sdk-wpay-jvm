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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mandatory data object containing response
 */
@ApiModel(description = "Mandatory data object containing response")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-29T17:25:37.973+10:00[Australia/Melbourne]")
public class MerchantSchemaDetailsResultData {
  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_SCHEMA = "schema";
  @SerializedName(SERIALIZED_NAME_SCHEMA)
  private Map<String, Object> schema = new HashMap<String, Object>();


  public MerchantSchemaDetailsResultData type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * The type of the schema e.g. pos, mewrchant
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type of the schema e.g. pos, mewrchant")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public MerchantSchemaDetailsResultData description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * A description for the schema
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A description for the schema")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public MerchantSchemaDetailsResultData schema(Map<String, Object> schema) {
    
    this.schema = schema;
    return this;
  }

  public MerchantSchemaDetailsResultData putSchemaItem(String key, Object schemaItem) {
    this.schema.put(key, schemaItem);
    return this;
  }

   /**
   * The schema content formatted according to JSON Schema standards
   * @return schema
  **/
  @ApiModelProperty(required = true, value = "The schema content formatted according to JSON Schema standards")

  public Map<String, Object> getSchema() {
    return schema;
  }


  public void setSchema(Map<String, Object> schema) {
    this.schema = schema;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchantSchemaDetailsResultData merchantSchemaDetailsResultData = (MerchantSchemaDetailsResultData) o;
    return Objects.equals(this.type, merchantSchemaDetailsResultData.type) &&
        Objects.equals(this.description, merchantSchemaDetailsResultData.description) &&
        Objects.equals(this.schema, merchantSchemaDetailsResultData.schema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, description, schema);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantSchemaDetailsResultData {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    schema: ").append(toIndentedString(schema)).append("\n");
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

