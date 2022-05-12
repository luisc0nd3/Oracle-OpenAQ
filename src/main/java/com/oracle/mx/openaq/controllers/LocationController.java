package com.oracle.mx.openaq.controllers;

import com.oracle.mx.openaq.models.dto.LocationDTO;
import com.oracle.mx.openaq.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

  public static final Logger logger = LoggerFactory.getLogger(LocationController.class);

  @Autowired private LocationService locationService;

  @PostMapping("/byId/{locationId}")
  public List<LocationDTO> getCitiesByCountry(@PathVariable BigInteger locationId) {
    return locationService.getLocationById(locationId);
  }
}
