package com.oracle.mx.openaq.services;

import com.oracle.mx.openaq.models.dto.LocationDTO;

import java.math.BigInteger;
import java.util.List;

public interface LocationService {

  List<LocationDTO> getLocationById(BigInteger locationId);
}
