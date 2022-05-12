package com.oracle.mx.openaq.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Parameter {
  private BigInteger id;
  private String unit;
  private BigInteger count;
  private BigDecimal average;
  private BigDecimal lastValue;
  private String parameterNode;
  private String displayName;
  private String lastUpdated;
  private BigInteger parameterId;
  private String firstUpdated;
}
