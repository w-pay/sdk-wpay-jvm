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
import au.com.woolworths.village.sdk.dto.MerchantSchemaSummary;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mandatory data object containing response
 */
@ApiModel(description = "Mandatory data object containing response")

public class MerchantSchemaResultData {
  public static final String SERIALIZED_NAME_SCHEMAS = "schemas";
  @SerializedName(SERIALIZED_NAME_SCHEMAS)
  private List<MerchantSchemaSummary> schemas = new ArrayList<MerchantSchemaSummary>();


  public MerchantSchemaResultData schemas(List<MerchantSchemaSummary> schemas) {
    
    this.schemas = schemas;
    return this;
  }

  public MerchantSchemaResultData addSchemasItem(MerchantSchemaSummary schemasItem) {
    this.schemas.add(schemasItem);
    return this;
  }

   /**
   * A list of IDs for the currently valid schemas for this merchant
   * @return schemas
  **/
  @ApiModelProperty(required = true, value = "A list of IDs for the currently valid schemas for this merchant")

  public List<MerchantSchemaSummary> getSchemas() {
    return schemas;
  }


  public void setSchemas(List<MerchantSchemaSummary> schemas) {
    this.schemas = schemas;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchantSchemaResultData merchantSchemaResultData = (MerchantSchemaResultData) o;
    return Objects.equals(this.schemas, merchantSchemaResultData.schemas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemas);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantSchemaResultData {\n");
    sb.append("    schemas: ").append(toIndentedString(schemas)).append("\n");
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

