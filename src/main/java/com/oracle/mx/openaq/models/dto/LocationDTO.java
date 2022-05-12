package com.oracle.mx.openaq.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LocationDTO {
  private String name;
  private List<Source> sources;
  private List<Parameter> parameters;
  private String lastUpdated;
  private String firstUpdated;
  private BigInteger measurements;
}
