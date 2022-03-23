package com.example.task04app.external.alfabank.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Выходная модель, содержащая курсы валют Национального банка Республики Беларусь
 */
@ApiModel(description = "Выходная модель, содержащая курсы валют Национального банка Республики Беларусь")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-20T16:55:36.770+03:00")

public class NationalRateListResponse   {
  @JsonProperty("rates")
  @Valid
  private List<NationalRate> rates = null;

  public NationalRateListResponse rates(List<NationalRate> rates) {
    this.rates = rates;
    return this;
  }

  public NationalRateListResponse addRatesItem(NationalRate ratesItem) {
    if (this.rates == null) {
      this.rates = new ArrayList<NationalRate>();
    }
    this.rates.add(ratesItem);
    return this;
  }

  /**
   * Список курсов валют Национального банка Республики Беларусь
   * @return rates
  **/
  @ApiModelProperty(value = "Список курсов валют Национального банка Республики Беларусь")

  @Valid

  public List<NationalRate> getRates() {
    return rates;
  }

  public void setRates(List<NationalRate> rates) {
    this.rates = rates;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NationalRateListResponse nationalRateListResponse = (NationalRateListResponse) o;
    return Objects.equals(this.rates, nationalRateListResponse.rates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NationalRateListResponse {\n");
    
    sb.append("    rates: ").append(toIndentedString(rates)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

