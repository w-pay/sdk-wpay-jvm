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
import java.io.Serializable;

/**
 * CustomerPreferencesResult
 */

public class CustomerPreferencesResult implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SERIALIZED_NAME_DATA = "data";
  @SerializedName(SERIALIZED_NAME_DATA)
  private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

  public static final String SERIALIZED_NAME_META = "meta";
  @SerializedName(SERIALIZED_NAME_META)
  private Map<String, Object> meta = new HashMap<String, Object>();


  public CustomerPreferencesResult data(Map<String, Map<String, String>> data) {
    
    this.data = data;
    return this;
  }

  public CustomerPreferencesResult putDataItem(String key, Map<String, String> dataItem) {
    this.data.put(key, dataItem);
    return this;
  }

   /**
   * A set of groups of key/value based preferences
   * @return data
  **/
  @ApiModelProperty(required = true, value = "A set of groups of key/value based preferences")

  public Map<String, Map<String, String>> getData() {
    return data;
  }


  public void setData(Map<String, Map<String, String>> data) {
    this.data = data;
  }


  public CustomerPreferencesResult meta(Map<String, Object> meta) {
    
    this.meta = meta;
    return this;
  }

  public CustomerPreferencesResult putMetaItem(String key, Object metaItem) {
    this.meta.put(key, metaItem);
    return this;
  }

   /**
   * Object to contain any metadata
   * @return meta
  **/
  @ApiModelProperty(required = true, value = "Object to contain any metadata")

  public Map<String, Object> getMeta() {
    return meta;
  }


  public void setMeta(Map<String, Object> meta) {
    this.meta = meta;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerPreferencesResult customerPreferencesResult = (CustomerPreferencesResult) o;
    return Objects.equals(this.data, customerPreferencesResult.data) &&
        Objects.equals(this.meta, customerPreferencesResult.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, meta);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerPreferencesResult {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
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

