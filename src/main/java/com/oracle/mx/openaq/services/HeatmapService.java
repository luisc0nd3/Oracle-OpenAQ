package com.oracle.mx.openaq.services;

import com.oracle.mx.openaq.models.dto.HeatmapDTO;

import java.util.List;

public interface HeatmapService {
  List<HeatmapDTO> getHeatmap(String country, String param);
}
