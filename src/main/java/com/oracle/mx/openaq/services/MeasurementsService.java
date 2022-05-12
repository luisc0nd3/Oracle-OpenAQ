package com.oracle.mx.openaq.services;

import com.oracle.mx.openaq.models.dto.MeasurementsResult;

import java.util.List;

public interface MeasurementsService {
  List<MeasurementsResult> getAll();
}
