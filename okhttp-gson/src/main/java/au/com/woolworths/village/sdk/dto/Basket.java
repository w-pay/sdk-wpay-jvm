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
import au.com.woolworths.village.sdk.dto.BasketItems;
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
 * Information provided by the Merchant on the basket of items associated with the payment request.  This payload originates in the payment request and is carried with any resulting transactions
 */
@ApiModel(description = "Information provided by the Merchant on the basket of items associated with the payment request.  This payload originates in the payment request and is carried with any resulting transactions")

public class Basket {
  public static final String SERIALIZED_NAME_ITEMS = "items";
  @SerializedName(SERIALIZED_NAME_ITEMS)
  private List<BasketItems> items = null;


  public Basket items(List<BasketItems> items) {
    
    this.items = items;
    return this;
  }

  public Basket addItemsItem(BasketItems itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<BasketItems>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * An array of items in the basket
   * @return items
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An array of items in the basket")

  public List<BasketItems> getItems() {
    return items;
  }


  public void setItems(List<BasketItems> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Basket basket = (Basket) o;
    return Objects.equals(this.items, basket.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Basket {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

