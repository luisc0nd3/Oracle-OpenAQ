package com.oracle.mx.openaq.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HeatmapDTO {
  private BigInteger locationId;
  private String location;
  private String parameter;
  private BigDecimal value;
  private String unit;
  private Coordinates coordinates;
  private String country;
  private String hexColor;
  private Long level;
  private List<String> cities;
  private List<LocationDTO> locationDTO;
}
