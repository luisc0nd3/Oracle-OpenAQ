package com.oracle.mx.openaq.controllers;

import com.oracle.mx.openaq.services.CitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cities")
public class CitiesController {

  public static final Logger logger = LoggerFactory.getLogger(CitiesController.class);

  @Autowired private CitiesService citiesService;

  @PostMapping("/all")
  public List<String> getAllCities() {

    logger.debug("Begin getAll to Measurements controller");

    return citiesService.getAllCities();
  }

  @PostMapping("/byCountry/{country}")
  public List<String> getCitiesByCountry(@PathVariable String country) {

    logger.debug("into getCitiesByCountry with country=");
    logger.debug(country);

    return citiesService.getCitiesByCountry(country);
  }
}
