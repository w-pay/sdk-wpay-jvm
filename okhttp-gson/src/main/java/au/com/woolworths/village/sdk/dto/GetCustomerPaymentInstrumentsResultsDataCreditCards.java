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
import au.com.woolworths.village.sdk.dto.GetCustomerPaymentInstrumentsResultsDataStepUp;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * GetCustomerPaymentInstrumentsResultsDataCreditCards
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-04-29T17:25:37.973+10:00[Australia/Melbourne]")
public class GetCustomerPaymentInstrumentsResultsDataCreditCards {
  public static final String SERIALIZED_NAME_PAYMENT_INSTRUMENT_ID = "paymentInstrumentId";
  @SerializedName(SERIALIZED_NAME_PAYMENT_INSTRUMENT_ID)
  private String paymentInstrumentId;

  public static final String SERIALIZED_NAME_PAYMENT_TOKEN = "paymentToken";
  @SerializedName(SERIALIZED_NAME_PAYMENT_TOKEN)
  private String paymentToken;

  /**
   * The status of the payment instrument in the container.
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    UNVERIFIED_PERSISTENT("UNVERIFIED_PERSISTENT"),
    
    VERIFIED("VERIFIED");

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

  public static final String SERIALIZED_NAME_LAST_UPDATED = "lastUpdated";
  @SerializedName(SERIALIZED_NAME_LAST_UPDATED)
  private String lastUpdated;

  public static final String SERIALIZED_NAME_LAST_USED = "lastUsed";
  @SerializedName(SERIALIZED_NAME_LAST_USED)
  private String lastUsed;

  public static final String SERIALIZED_NAME_PRIMARY = "primary";
  @SerializedName(SERIALIZED_NAME_PRIMARY)
  private Boolean primary;

  public static final String SERIALIZED_NAME_ALLOWED = "allowed";
  @SerializedName(SERIALIZED_NAME_ALLOWED)
  private Boolean allowed;

  public static final String SERIALIZED_NAME_EXPIRY_YEAR = "expiryYear";
  @SerializedName(SERIALIZED_NAME_EXPIRY_YEAR)
  private String expiryYear;

  public static final String SERIALIZED_NAME_EXPIRY_MONTH = "expiryMonth";
  @SerializedName(SERIALIZED_NAME_EXPIRY_MONTH)
  private String expiryMonth;

  public static final String SERIALIZED_NAME_SCHEME = "scheme";
  @SerializedName(SERIALIZED_NAME_SCHEME)
  private String scheme;

  public static final String SERIALIZED_NAME_CARD_SUFFIX = "cardSuffix";
  @SerializedName(SERIALIZED_NAME_CARD_SUFFIX)
  private String cardSuffix;

  public static final String SERIALIZED_NAME_CVV_VALIDATED = "cvvValidated";
  @SerializedName(SERIALIZED_NAME_CVV_VALIDATED)
  private Boolean cvvValidated;

  public static final String SERIALIZED_NAME_CARD_NAME = "cardName";
  @SerializedName(SERIALIZED_NAME_CARD_NAME)
  private String cardName;

  public static final String SERIALIZED_NAME_EXPIRED = "expired";
  @SerializedName(SERIALIZED_NAME_EXPIRED)
  private Boolean expired;

  public static final String SERIALIZED_NAME_REQUIRES_C_V_V = "requiresCVV";
  @SerializedName(SERIALIZED_NAME_REQUIRES_C_V_V)
  private Boolean requiresCVV;

  public static final String SERIALIZED_NAME_UPDATE_U_R_L = "updateURL";
  @SerializedName(SERIALIZED_NAME_UPDATE_U_R_L)
  private String updateURL;

  public static final String SERIALIZED_NAME_STEP_UP = "stepUp";
  @SerializedName(SERIALIZED_NAME_STEP_UP)
  private GetCustomerPaymentInstrumentsResultsDataStepUp stepUp;


  public GetCustomerPaymentInstrumentsResultsDataCreditCards paymentInstrumentId(String paymentInstrumentId) {
    
    this.paymentInstrumentId = paymentInstrumentId;
    return this;
  }

   /**
   * The credit card payment instrument id.
   * @return paymentInstrumentId
  **/
  @ApiModelProperty(example = "90731", required = true, value = "The credit card payment instrument id.")

  public String getPaymentInstrumentId() {
    return paymentInstrumentId;
  }


  public void setPaymentInstrumentId(String paymentInstrumentId) {
    this.paymentInstrumentId = paymentInstrumentId;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards paymentToken(String paymentToken) {
    
    this.paymentToken = paymentToken;
    return this;
  }

   /**
   * The credit card payment token. The payment token is a unique identifier for the payment instrument.
   * @return paymentToken
  **/
  @ApiModelProperty(example = "712029a1-c0aa-41bc-a810-3d42424c5834", required = true, value = "The credit card payment token. The payment token is a unique identifier for the payment instrument.")

  public String getPaymentToken() {
    return paymentToken;
  }


  public void setPaymentToken(String paymentToken) {
    this.paymentToken = paymentToken;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * The status of the payment instrument in the container.
   * @return status
  **/
  @ApiModelProperty(required = true, value = "The status of the payment instrument in the container.")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards lastUpdated(String lastUpdated) {
    
    this.lastUpdated = lastUpdated;
    return this;
  }

   /**
   * The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601.
   * @return lastUpdated
  **/
  @ApiModelProperty(example = "2017-11-06T19:38:09.890+11:00", required = true, value = "The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601.")

  public String getLastUpdated() {
    return lastUpdated;
  }


  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards lastUsed(String lastUsed) {
    
    this.lastUsed = lastUsed;
    return this;
  }

   /**
   * The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used.
   * @return lastUsed
  **/
  @ApiModelProperty(example = "2017-11-06T19:38:09.890+11:00", required = true, value = "The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used.")

  public String getLastUsed() {
    return lastUsed;
  }


  public void setLastUsed(String lastUsed) {
    this.lastUsed = lastUsed;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards primary(Boolean primary) {
    
    this.primary = primary;
    return this;
  }

   /**
   * A flag to indicate if this payment instrument is the primary instrument in the container.
   * @return primary
  **/
  @ApiModelProperty(example = "true", required = true, value = "A flag to indicate if this payment instrument is the primary instrument in the container.")

  public Boolean getPrimary() {
    return primary;
  }


  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards allowed(Boolean allowed) {
    
    this.allowed = allowed;
    return this;
  }

   /**
   * A flag to indicate if the merchant profile in the container allows the use of this payment instrument.
   * @return allowed
  **/
  @ApiModelProperty(example = "true", required = true, value = "A flag to indicate if the merchant profile in the container allows the use of this payment instrument.")

  public Boolean getAllowed() {
    return allowed;
  }


  public void setAllowed(Boolean allowed) {
    this.allowed = allowed;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards expiryYear(String expiryYear) {
    
    this.expiryYear = expiryYear;
    return this;
  }

   /**
   * The year of the expiry date of the credit card.
   * @return expiryYear
  **/
  @ApiModelProperty(example = "21", required = true, value = "The year of the expiry date of the credit card.")

  public String getExpiryYear() {
    return expiryYear;
  }


  public void setExpiryYear(String expiryYear) {
    this.expiryYear = expiryYear;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards expiryMonth(String expiryMonth) {
    
    this.expiryMonth = expiryMonth;
    return this;
  }

   /**
   * The month of the expiry date of the credit card.
   * @return expiryMonth
  **/
  @ApiModelProperty(example = "05", required = true, value = "The month of the expiry date of the credit card.")

  public String getExpiryMonth() {
    return expiryMonth;
  }


  public void setExpiryMonth(String expiryMonth) {
    this.expiryMonth = expiryMonth;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards scheme(String scheme) {
    
    this.scheme = scheme;
    return this;
  }

   /**
   * The credit card scheme.
   * @return scheme
  **/
  @ApiModelProperty(example = "MASTERCARD", required = true, value = "The credit card scheme.")

  public String getScheme() {
    return scheme;
  }


  public void setScheme(String scheme) {
    this.scheme = scheme;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards cardSuffix(String cardSuffix) {
    
    this.cardSuffix = cardSuffix;
    return this;
  }

   /**
   * The suffix (last 4 digits) of the credit card number.
   * @return cardSuffix
  **/
  @ApiModelProperty(example = "6106", required = true, value = "The suffix (last 4 digits) of the credit card number.")

  public String getCardSuffix() {
    return cardSuffix;
  }


  public void setCardSuffix(String cardSuffix) {
    this.cardSuffix = cardSuffix;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards cvvValidated(Boolean cvvValidated) {
    
    this.cvvValidated = cvvValidated;
    return this;
  }

   /**
   * A flag to indicate if the CVV of the credit card has been validated.
   * @return cvvValidated
  **/
  @ApiModelProperty(example = "true", required = true, value = "A flag to indicate if the CVV of the credit card has been validated.")

  public Boolean getCvvValidated() {
    return cvvValidated;
  }


  public void setCvvValidated(Boolean cvvValidated) {
    this.cvvValidated = cvvValidated;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards cardName(String cardName) {
    
    this.cardName = cardName;
    return this;
  }

   /**
   * The nickname of the credit card instrument in the container.
   * @return cardName
  **/
  @ApiModelProperty(example = "My Card", required = true, value = "The nickname of the credit card instrument in the container.")

  public String getCardName() {
    return cardName;
  }


  public void setCardName(String cardName) {
    this.cardName = cardName;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards expired(Boolean expired) {
    
    this.expired = expired;
    return this;
  }

   /**
   * A flag to indicate if the credit card is expired.
   * @return expired
  **/
  @ApiModelProperty(example = "true", required = true, value = "A flag to indicate if the credit card is expired.")

  public Boolean getExpired() {
    return expired;
  }


  public void setExpired(Boolean expired) {
    this.expired = expired;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards requiresCVV(Boolean requiresCVV) {
    
    this.requiresCVV = requiresCVV;
    return this;
  }

   /**
   * A flag to indicate if payments with this credit card requires a CVV check.
   * @return requiresCVV
  **/
  @ApiModelProperty(example = "true", required = true, value = "A flag to indicate if payments with this credit card requires a CVV check.")

  public Boolean getRequiresCVV() {
    return requiresCVV;
  }


  public void setRequiresCVV(Boolean requiresCVV) {
    this.requiresCVV = requiresCVV;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards updateURL(String updateURL) {
    
    this.updateURL = updateURL;
    return this;
  }

   /**
   * The URL of an iframe. This iframe is used to capture a credit card expiry and CVV.
   * @return updateURL
  **/
  @ApiModelProperty(example = "https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/cvvExpiry/353629ec-4cb5-4fc3-ab47-8c9c3f117ab8/90731", required = true, value = "The URL of an iframe. This iframe is used to capture a credit card expiry and CVV.")

  public String getUpdateURL() {
    return updateURL;
  }


  public void setUpdateURL(String updateURL) {
    this.updateURL = updateURL;
  }


  public GetCustomerPaymentInstrumentsResultsDataCreditCards stepUp(GetCustomerPaymentInstrumentsResultsDataStepUp stepUp) {
    
    this.stepUp = stepUp;
    return this;
  }

   /**
   * Get stepUp
   * @return stepUp
  **/
  @ApiModelProperty(required = true, value = "")

  public GetCustomerPaymentInstrumentsResultsDataStepUp getStepUp() {
    return stepUp;
  }


  public void setStepUp(GetCustomerPaymentInstrumentsResultsDataStepUp stepUp) {
    this.stepUp = stepUp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCustomerPaymentInstrumentsResultsDataCreditCards getCustomerPaymentInstrumentsResultsDataCreditCards = (GetCustomerPaymentInstrumentsResultsDataCreditCards) o;
    return Objects.equals(this.paymentInstrumentId, getCustomerPaymentInstrumentsResultsDataCreditCards.paymentInstrumentId) &&
        Objects.equals(this.paymentToken, getCustomerPaymentInstrumentsResultsDataCreditCards.paymentToken) &&
        Objects.equals(this.status, getCustomerPaymentInstrumentsResultsDataCreditCards.status) &&
        Objects.equals(this.lastUpdated, getCustomerPaymentInstrumentsResultsDataCreditCards.lastUpdated) &&
        Objects.equals(this.lastUsed, getCustomerPaymentInstrumentsResultsDataCreditCards.lastUsed) &&
        Objects.equals(this.primary, getCustomerPaymentInstrumentsResultsDataCreditCards.primary) &&
        Objects.equals(this.allowed, getCustomerPaymentInstrumentsResultsDataCreditCards.allowed) &&
        Objects.equals(this.expiryYear, getCustomerPaymentInstrumentsResultsDataCreditCards.expiryYear) &&
        Objects.equals(this.expiryMonth, getCustomerPaymentInstrumentsResultsDataCreditCards.expiryMonth) &&
        Objects.equals(this.scheme, getCustomerPaymentInstrumentsResultsDataCreditCards.scheme) &&
        Objects.equals(this.cardSuffix, getCustomerPaymentInstrumentsResultsDataCreditCards.cardSuffix) &&
        Objects.equals(this.cvvValidated, getCustomerPaymentInstrumentsResultsDataCreditCards.cvvValidated) &&
        Objects.equals(this.cardName, getCustomerPaymentInstrumentsResultsDataCreditCards.cardName) &&
        Objects.equals(this.expired, getCustomerPaymentInstrumentsResultsDataCreditCards.expired) &&
        Objects.equals(this.requiresCVV, getCustomerPaymentInstrumentsResultsDataCreditCards.requiresCVV) &&
        Objects.equals(this.updateURL, getCustomerPaymentInstrumentsResultsDataCreditCards.updateURL) &&
        Objects.equals(this.stepUp, getCustomerPaymentInstrumentsResultsDataCreditCards.stepUp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentInstrumentId, paymentToken, status, lastUpdated, lastUsed, primary, allowed, expiryYear, expiryMonth, scheme, cardSuffix, cvvValidated, cardName, expired, requiresCVV, updateURL, stepUp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCustomerPaymentInstrumentsResultsDataCreditCards {\n");
    sb.append("    paymentInstrumentId: ").append(toIndentedString(paymentInstrumentId)).append("\n");
    sb.append("    paymentToken: ").append(toIndentedString(paymentToken)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
    sb.append("    lastUsed: ").append(toIndentedString(lastUsed)).append("\n");
    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    allowed: ").append(toIndentedString(allowed)).append("\n");
    sb.append("    expiryYear: ").append(toIndentedString(expiryYear)).append("\n");
    sb.append("    expiryMonth: ").append(toIndentedString(expiryMonth)).append("\n");
    sb.append("    scheme: ").append(toIndentedString(scheme)).append("\n");
    sb.append("    cardSuffix: ").append(toIndentedString(cardSuffix)).append("\n");
    sb.append("    cvvValidated: ").append(toIndentedString(cvvValidated)).append("\n");
    sb.append("    cardName: ").append(toIndentedString(cardName)).append("\n");
    sb.append("    expired: ").append(toIndentedString(expired)).append("\n");
    sb.append("    requiresCVV: ").append(toIndentedString(requiresCVV)).append("\n");
    sb.append("    updateURL: ").append(toIndentedString(updateURL)).append("\n");
    sb.append("    stepUp: ").append(toIndentedString(stepUp)).append("\n");
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

