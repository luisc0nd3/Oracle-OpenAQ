package com.oracle.mx.openaq.services.impl;

import com.oracle.mx.openaq.models.dto.Coordinates;
import com.oracle.mx.openaq.models.dto.HeatmapDTO;
import com.oracle.mx.openaq.models.dto.MeasurementsResult;
import com.oracle.mx.openaq.services.CitiesService;
import com.oracle.mx.openaq.services.HeatmapService;
import com.oracle.mx.openaq.services.LocationService;
import com.oracle.mx.openaq.services.MeasurementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HeatmapServiceImpl implements HeatmapService {

  public static final Logger logger = LoggerFactory.getLogger(HeatmapServiceImpl.class);

  @Autowired private CitiesService citiesService;

  @Autowired private MeasurementsService measurementsService;

  @Autowired private LocationService locationService;

  @Override
  public List<HeatmapDTO> getHeatmap(String country, String param) {

    return applyFilters(country, param, measurementsService.getAll());
  }

  private List<HeatmapDTO> applyFilters(
      String country, String param, List<MeasurementsResult> listMeasurements) {

    logger.debug("into applyFilters");

    List<HeatmapDTO> listCountries = new ArrayList();
    HeatmapDTO heatmapDTO;

    try {
      for (MeasurementsResult m : listMeasurements) {
        if ((m.getCountry() != null && m.getParameter() != null)
            && (m.getCountry().equals(country) && m.getParameter().equals(param))) {

          heatmapDTO = new HeatmapDTO();
          heatmapDTO.setCountry(country);
          heatmapDTO.setCoordinates(
              (m.getCoordinates() != null) ? m.getCoordinates() : new Coordinates());
          heatmapDTO.setParameter(param);
          heatmapDTO.setUnit((m.getUnit() != null) ? m.getUnit() : "");
          heatmapDTO.setLocation((m.getLocation() != null) ? m.getLocation() : "");
          heatmapDTO.setValue((m.getValue() != null) ? m.getValue() : new BigDecimal("0"));
          heatmapDTO.setLocationId(
              (m.getLocationId() != null) ? m.getLocationId() : BigInteger.valueOf(0));
          heatmapDTO.setCities(citiesService.getCitiesByCountry(country));
          heatmapDTO.setLocationDTO(locationService.getLocationById(m.getLocationId()));

          listCountries.add(heatmapDTO);
        }
      }
    } catch (NullPointerException e) {
      logger.debug(e.getMessage());
      return listCountries;
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    listCountries.sort((HeatmapDTO h1, HeatmapDTO h2) -> h1.getValue().compareTo(h2.getValue()));

    return applyColor(listCountries);
  }

  private List<HeatmapDTO> applyColor(List<HeatmapDTO> listCountries) {
    logger.debug("into applyColor");

    int r = 245;
    int g = 255;
    int b = 9;

    long level = 1;

    for (HeatmapDTO h : listCountries) {
      h.setHexColor(String.format("#%02x%02x%02x", r, g--, b));
      h.setLevel(level++);
    }

    return listCountries;
  }
}
