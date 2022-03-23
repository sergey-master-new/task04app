package com.example.task04app.external.alfabank.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
//import org.threeten.bp.LocalDate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Курс валюты банка
 */
@ApiModel(description = "Курс валюты банка")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-20T16:55:36.770+03:00")

public class Rate   {

  @JsonProperty("sellRate")
  private BigDecimal sellRate = null;

  @JsonProperty("sellIso")
  private String sellIso = null;

  @JsonProperty("sellCode")
  private Integer sellCode = null;

  @JsonProperty("buyRate")
  private BigDecimal buyRate = null;

  @JsonProperty("buyIso")
  private String buyIso = null;

  @JsonProperty("buyCode")
  private Integer buyCode = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("name")
  private String name = null;

  @JsonIgnore
  private LocalDate date = null;

  public Rate sellRate(BigDecimal sellRate) {
    this.sellRate = sellRate;
    return this;
  }

  /**
   * Курс продажи
   * @return sellRate
  **/
  @ApiModelProperty(value = "Курс продажи")

  @Valid

  public BigDecimal getSellRate() {
    return sellRate;
  }

  public void setSellRate(BigDecimal sellRate) {
    this.sellRate = sellRate;
  }

  public Rate sellIso(String sellIso) {
    this.sellIso = sellIso;
    return this;
  }

  /**
   * Мнемоника продаваемой валюты
   * @return sellIso
  **/
  @ApiModelProperty(value = "Мнемоника продаваемой валюты")


  public String getSellIso() {
    return sellIso;
  }

  public void setSellIso(String sellIso) {
    this.sellIso = sellIso;
  }

  public Rate sellCode(Integer sellCode) {
    this.sellCode = sellCode;
    return this;
  }

  /**
   * Код продаваемой валюты
   * @return sellCode
  **/
  @ApiModelProperty(value = "Код продаваемой валюты")


  public Integer getSellCode() {
    return sellCode;
  }

  public void setSellCode(Integer sellCode) {
    this.sellCode = sellCode;
  }

  public Rate buyRate(BigDecimal buyRate) {
    this.buyRate = buyRate;
    return this;
  }

  /**
   * Курс покупки
   * @return buyRate
  **/
  @ApiModelProperty(value = "Курс покупки")

  @Valid

  public BigDecimal getBuyRate() {
    return buyRate;
  }

  public void setBuyRate(BigDecimal buyRate) {
    this.buyRate = buyRate;
  }

  public Rate buyIso(String buyIso) {
    this.buyIso = buyIso;
    return this;
  }

  /**
   * Мнемоника покупаемой валюты
   * @return buyIso
  **/
  @ApiModelProperty(value = "Мнемоника покупаемой валюты")


  public String getBuyIso() {
    return buyIso;
  }

  public void setBuyIso(String buyIso) {
    this.buyIso = buyIso;
  }

  public Rate buyCode(Integer buyCode) {
    this.buyCode = buyCode;
    return this;
  }

  /**
   * Код покупаемой валюты
   * @return buyCode
  **/
  @ApiModelProperty(value = "Код покупаемой валюты")


  public Integer getBuyCode() {
    return buyCode;
  }

  public void setBuyCode(Integer buyCode) {
    this.buyCode = buyCode;
  }

  public Rate quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Количество валюты (лот)
   * @return quantity
  **/
  @ApiModelProperty(value = "Количество валюты (лот)")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Rate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Наименование валюты
   * @return name
  **/
  @ApiModelProperty(value = "Наименование валюты")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Rate date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Дата начала действия курса
   * @return date
  **/
  @ApiModelProperty(example = "06.08.2019", value = "Дата начала действия курса")

  @Valid

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rate rate = (Rate) o;
    return Objects.equals(this.sellRate, rate.sellRate) &&
        Objects.equals(this.sellIso, rate.sellIso) &&
        Objects.equals(this.sellCode, rate.sellCode) &&
        Objects.equals(this.buyRate, rate.buyRate) &&
        Objects.equals(this.buyIso, rate.buyIso) &&
        Objects.equals(this.buyCode, rate.buyCode) &&
        Objects.equals(this.quantity, rate.quantity) &&
        Objects.equals(this.name, rate.name) &&
        Objects.equals(this.date, rate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellRate, sellIso, sellCode, buyRate, buyIso, buyCode, quantity, name, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rate {\n");
    
    sb.append("    sellRate: ").append(toIndentedString(sellRate)).append("\n");
    sb.append("    sellIso: ").append(toIndentedString(sellIso)).append("\n");
    sb.append("    sellCode: ").append(toIndentedString(sellCode)).append("\n");
    sb.append("    buyRate: ").append(toIndentedString(buyRate)).append("\n");
    sb.append("    buyIso: ").append(toIndentedString(buyIso)).append("\n");
    sb.append("    buyCode: ").append(toIndentedString(buyCode)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

