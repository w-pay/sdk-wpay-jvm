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
import au.com.woolworths.village.sdk.dto.CommonPaymentBase;
import au.com.woolworths.village.sdk.dto.CustomerPaymentDetailAllOf;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Detailed information for a single payment request
 */
@ApiModel(description = "Detailed information for a single payment request")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-05-01T10:39:59.330+10:00[Australia/Melbourne]")
public class CustomerPaymentDetail extends CommonPaymentBase {
  public static final String SERIALIZED_NAME_MERCHANT_ID = "merchantId";
  @SerializedName(SERIALIZED_NAME_MERCHANT_ID)
  private String merchantId;

  public static final String SERIALIZED_NAME_BASKET = "basket";
  @SerializedName(SERIALIZED_NAME_BASKET)
  private Basket basket;


  public CustomerPaymentDetail merchantId(String merchantId) {
    
    this.merchantId = merchantId;
    return this;
  }

   /**
   * The ID of the merchant associated with this transaction
   * @return merchantId
  **/
  @ApiModelProperty(required = true, value = "The ID of the merchant associated with this transaction")

  public String getMerchantId() {
    return merchantId;
  }


  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }


  public CustomerPaymentDetail basket(Basket basket) {
    
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerPaymentDetail customerPaymentDetail = (CustomerPaymentDetail) o;
    return Objects.equals(this.merchantId, customerPaymentDetail.merchantId) &&
        Objects.equals(this.basket, customerPaymentDetail.basket) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantId, basket, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerPaymentDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    merchantId: ").append(toIndentedString(merchantId)).append("\n");
    sb.append("    basket: ").append(toIndentedString(basket)).append("\n");
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

