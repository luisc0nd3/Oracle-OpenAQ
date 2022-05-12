package com.oracle.mx.openaq.services.impl;

import com.google.gson.Gson;
import com.oracle.mx.openaq.models.dto.MeasurementsResult;
import com.oracle.mx.openaq.services.MeasurementsService;
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
public class MeasurementsServiceImpl implements MeasurementsService {

  public static final Logger logger = LoggerFactory.getLogger(MeasurementsServiceImpl.class);

  @Autowired private RestTemplate restTemplate;

  @Override
  public List<MeasurementsResult> getAll() {

    logger.debug("into getAll to Measurements service");

    List<MeasurementsResult> listMeasurements = new ArrayList();
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
              "https://docs.openaq.org/v2/measurements",
              HttpMethod.GET,
              entity,
              new ParameterizedTypeReference<String>() {});

      JSONObject jsonObject = new JSONObject(response.getBody());
      JSONArray results = jsonObject.getJSONArray("results");

      Gson gson = new Gson();
      for (Object p : results) {
        MeasurementsResult measurement = gson.fromJson(p.toString(), MeasurementsResult.class);
        listMeasurements.add(measurement);
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    return listMeasurements;
  }
}
