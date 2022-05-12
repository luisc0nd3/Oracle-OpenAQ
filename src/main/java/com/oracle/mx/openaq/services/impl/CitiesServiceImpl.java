package com.oracle.mx.openaq.services.impl;

import com.google.gson.Gson;
import com.oracle.mx.openaq.models.dto.City;
import com.oracle.mx.openaq.services.CitiesService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {

  public static final Logger logger = LoggerFactory.getLogger(CitiesServiceImpl.class);

  @Autowired private RestTemplate restTemplate;

  @Override
  public List<String> getAllCities() {
    logger.debug("Into getAllCities service");

    List<String> listCities = new ArrayList();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    restTemplate
        .getMessageConverters()
        .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

    try {
      ResponseEntity<String> response =
          restTemplate.exchange(
              "https://docs.openaq.org/v2/cities",
              HttpMethod.GET,
              entity,
              new ParameterizedTypeReference<String>() {});

      logger.debug("Response: ");
      logger.debug(response.getBody());

      JSONObject jsonObject = new JSONObject(response.getBody());
      JSONArray results = jsonObject.getJSONArray("results");

      Gson gson = new Gson();
      for (Object p : results) {
        City city = gson.fromJson(p.toString(), City.class);
        listCities.add(city.getCity());
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    return listCities;
  }

  @Override
  public List<String> getCitiesByCountry(String country) {

    logger.debug("Into getCitiesByCountry service");

    List<String> listCities = new ArrayList();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    restTemplate
        .getMessageConverters()
        .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

    try {
      ResponseEntity<String> response =
          restTemplate.exchange(
              "https://docs.openaq.org/v2/cities",
              HttpMethod.GET,
              entity,
              new ParameterizedTypeReference<String>() {});

      logger.debug("Response: ");
      logger.debug(response.getBody());

      JSONObject jsonObject = new JSONObject(response.getBody());

      JSONArray results = jsonObject.getJSONArray("results");

      Gson gson = new Gson();
      for (Object p : results) {
        City city = gson.fromJson(p.toString(), City.class);

        if (city.getCountry().equals(country)) {
          listCities.add(city.getCity());
        }
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    return listCities;
  }
}
