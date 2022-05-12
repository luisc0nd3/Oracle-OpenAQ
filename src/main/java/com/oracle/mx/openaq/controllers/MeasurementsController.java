package com.oracle.mx.openaq.controllers;

import com.oracle.mx.openaq.models.dto.MeasurementsResult;
import com.oracle.mx.openaq.services.MeasurementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/Measurements")
public class MeasurementsController {

  public static final Logger logger = LoggerFactory.getLogger(MeasurementsController.class);

  @Autowired private MeasurementsService measurementsService;

  @PostMapping("/all")
  public List<MeasurementsResult> getAll() {

    logger.debug("Begin getAll to Measurements controller");

    return measurementsService.getAll();
  }
}
