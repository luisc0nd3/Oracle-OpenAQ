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
public class MeasurementsResult {
  private BigInteger locationId;
  private String location;
  private String parameter;
  private BigDecimal value;
  private DateMeasurement date;
  private String unit;
  private Coordinates coordinates;
  private String country;
  private String city;
  private Boolean isMobile;
  private Boolean isAnalysis;
  private String entity;
  private String sensorType;
}
