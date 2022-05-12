package com.oracle.mx.openaq.services;

import java.util.List;

public interface CitiesService {
  List<String> getAllCities();

  List<String> getCitiesByCountry(String country);
}
