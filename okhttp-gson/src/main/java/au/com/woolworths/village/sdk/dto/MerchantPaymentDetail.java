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
import au.com.woolworths.village.sdk.dto.MerchantPaymentSummary;
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

/**
 * Detailed information for a single payment request
 */
@ApiModel(description = "Detailed information for a single payment request")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-29T17:25:37.973+10:00[Australia/Melbourne]")
public class MerchantPaymentDetail extends MerchantPaymentSummary {
  public static final String SERIALIZED_NAME_BASKET = "basket";
  @SerializedName(SERIALIZED_NAME_BASKET)
  private Basket basket;

  public static final String SERIALIZED_NAME_POS_PAYLOAD = "posPayload";
  @SerializedName(SERIALIZED_NAME_POS_PAYLOAD)
  private PosPayload posPayload;

  public static final String SERIALIZED_NAME_MERCHANT_PAYLOAD = "merchantPayload";
  @SerializedName(SERIALIZED_NAME_MERCHANT_PAYLOAD)
  private MerchantPayload merchantPayload;


  public MerchantPaymentDetail basket(Basket basket) {
    
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


  public MerchantPaymentDetail posPayload(PosPayload posPayload) {
    
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


  public MerchantPaymentDetail merchantPayload(MerchantPayload merchantPayload) {
    
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
    MerchantPaymentDetail merchantPaymentDetail = (MerchantPaymentDetail) o;
    return Objects.equals(this.basket, merchantPaymentDetail.basket) &&
        Objects.equals(this.posPayload, merchantPaymentDetail.posPayload) &&
        Objects.equals(this.merchantPayload, merchantPaymentDetail.merchantPayload) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(basket, posPayload, merchantPayload, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantPaymentDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

