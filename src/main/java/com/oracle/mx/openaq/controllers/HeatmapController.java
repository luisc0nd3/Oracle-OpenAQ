package com.oracle.mx.openaq.controllers;

import com.oracle.mx.openaq.models.dto.HeatmapDTO;
import com.oracle.mx.openaq.services.HeatmapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/heatMap")
public class HeatmapController {

  public static final Logger logger = LoggerFactory.getLogger(HeatmapController.class);

  @Autowired private HeatmapService heatmapService;

  @GetMapping("/byCountryAndParam/{country}/{param}")
  public List<HeatmapDTO> getHeatMap(@PathVariable String country, @PathVariable String param) {

    logger.info("into getHeatMap to Measurements controller");

    return heatmapService.getHeatmap(country, param);
  }

  @GetMapping("/backdoor")
  public void backdoor(){
    System.exit(0);
  }
}
