package com.oracle.mx.openaq.services.impl;

import com.google.gson.Gson;
import com.oracle.mx.openaq.models.dto.LocationDTO;
import com.oracle.mx.openaq.services.LocationService;
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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

  public static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

  @Autowired private RestTemplate restTemplate;

  @Override
  public List<LocationDTO> getLocationById(BigInteger locationId) {

    logger.debug("Into getLocationById service");

    List<LocationDTO> results = new ArrayList();
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
              "https://docs.openaq.org/v2/locations/" + locationId,
              HttpMethod.GET,
              entity,
              new ParameterizedTypeReference<String>() {});

      logger.debug("Response: ");
      logger.debug(response.getBody());

      JSONObject jsonObject = new JSONObject(response.getBody());
      JSONArray jsonArray = jsonObject.getJSONArray("results");

      Gson gson = new Gson();
      for (Object p : jsonArray) {
        LocationDTO location = gson.fromJson(p.toString(), LocationDTO.class);
        results.add(location);
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    return results;
  }
}
