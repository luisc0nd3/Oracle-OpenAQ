package com.oracle.mx.openaq.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class City {
  private String country;
  private String city;
  private Long count;
  private Integer locations;
  private String firstUpdated;
  private String lastUpdated;
  private List<String> parameters;
}
