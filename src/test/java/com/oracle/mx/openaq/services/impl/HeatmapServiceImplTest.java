package com.oracle.mx.openaq.services.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class HeatmapServiceImplTest {

  @Mock private RestTemplate restTemplate;

  @Test
  public void testGetEntityWithUsAndPm10() throws URISyntaxException {
    final String baseUrl = "http://localhost:8083/oracle/heatMap/byCountryAndParam/US/pm10";
    URI uri = new URI(baseUrl);

    Mockito.when(restTemplate.getForEntity(uri, String.class))
        .thenReturn(new ResponseEntity(entity, HttpStatus.OK));

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(true, result.getBody().contains("pm10"));
  }

  @Test
  public void testGetEntityWithMxAndPm10() throws URISyntaxException {
    final String baseUrl = "http://localhost:8083/oracle/heatMap/byCountryAndParam/US/pm10";
    URI uri = new URI(baseUrl);

    Mockito.when(restTemplate.getForEntity(uri, String.class))
        .thenReturn(new ResponseEntity(entity, HttpStatus.OK));

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(false, result.getBody().contains("MX"));
  }

  @Test
  public void testWhenUriFail() throws URISyntaxException {
    final String baseUrl = "http://localhost:8083/oracle/heatMap/byCountryAndParam/US/pm10";
    URI uri = new URI(baseUrl);

    Mockito.when(restTemplate.getForEntity(uri, String.class))
        .thenReturn(new ResponseEntity(entity, HttpStatus.NOT_FOUND));

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    Assert.assertEquals(404, result.getStatusCodeValue());
  }

  @Test
  public void testWhenEntityIsNull() throws URISyntaxException {
    final String baseUrl = "http://localhost:8083/oracle/heatMap/byCountryAndParam/US/pm10";
    URI uri = new URI(baseUrl);

    Mockito.when(restTemplate.getForEntity(uri, String.class))
        .thenReturn(new ResponseEntity(null, HttpStatus.OK));

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    Assert.assertNull(null, result.getBody());
  }

  private final String entity =
      "[\n"
          + "    {\n"
          + "        \"locationId\": 273070,\n"
          + "        \"location\": \"Cross Creek\",\n"
          + "        \"parameter\": \"pm10\",\n"
          + "        \"value\": 0,\n"
          + "        \"unit\": \"µg/m³\",\n"
          + "        \"coordinates\": {\n"
          + "            \"latitude\": 39.365124,\n"
          + "            \"longitude\": -119.81324\n"
          + "        },\n"
          + "        \"country\": \"US\",\n"
          + "        \"hexColor\": \"#f5fa09\",\n"
          + "        \"level\": 1,\n"
          + "        \"cities\": [\n"
          + "            \"007\",\n"
          + "            \"019\",\n"
          + "            \"037\",\n"
          + "            \"039\",\n"
          + "            \"047\",\n"
          + "            \"051\",\n"
          + "            \"077\",\n"
          + "            \"ABBEVILLE\",\n"
          + "            \"Aberdeen\",\n"
          + "            \"ADA\",\n"
          + "            \"ADAIR\",\n"
          + "            \"ADAMS\",\n"
          + "            \"Adrian\",\n"
          + "            \"Akron\",\n"
          + "            \"ALACHUA\",\n"
          + "            \"ALAMEDA\",\n"
          + "            \"Albany\",\n"
          + "            \"Albany-Lebanon\",\n"
          + "            \"Albany-Schenectady-Troy\",\n"
          + "            \"Albuquerque\",\n"
          + "            \"ALEXANDER\",\n"
          + "            \"Allegan\",\n"
          + "            \"ALLEGANY\",\n"
          + "            \"Allentown-Bethlehem-Easton\",\n"
          + "            \"Altoona\",\n"
          + "            \"AMADOR\",\n"
          + "            \"Amarillo\",\n"
          + "            \"Americus\",\n"
          + "            \"Ames\",\n"
          + "            \"Anchorage\",\n"
          + "            \"ANCHORAGE\",\n"
          + "            \"Anderson\",\n"
          + "            \"ANDERSON\",\n"
          + "            \"ANDREW\"\n"
          + "        ],\n"
          + "        \"locationDTO\": [\n"
          + "            {\n"
          + "                \"name\": \"Cross Creek\",\n"
          + "                \"sources\": [\n"
          + "                    {\n"
          + "                        \"id\": \"purpleair\",\n"
          + "                        \"url\": \"https://www2.purpleair.com/\",\n"
          + "                        \"name\": \"PurpleAir\"\n"
          + "                    }\n"
          + "                ],\n"
          + "                \"parameters\": [\n"
          + "                    {\n"
          + "                        \"id\": 1565477,\n"
          + "                        \"unit\": \"µg/m³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.740364758698092,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM2.5\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 2,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    },\n"
          + "                    {\n"
          + "                        \"id\": 1565478,\n"
          + "                        \"unit\": \"µg/m³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.341818181818182,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM1\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 19,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    },\n"
          + "                    {\n"
          + "                        \"id\": 1565473,\n"
          + "                        \"unit\": \"particles/cm³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.0000273101384212495,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM10 count\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 135,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    },\n"
          + "                    {\n"
          + "                        \"id\": 1565476,\n"
          + "                        \"unit\": \"µg/m³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.948264122708566,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM10\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 1,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    },\n"
          + "                    {\n"
          + "                        \"id\": 1565474,\n"
          + "                        \"unit\": \"particles/cm³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.00124635241301908,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM2.5 count\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 130,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    },\n"
          + "                    {\n"
          + "                        \"id\": 1565475,\n"
          + "                        \"unit\": \"particles/cm³\",\n"
          + "                        \"count\": 160380,\n"
          + "                        \"average\": 0.0475065469509915,\n"
          + "                        \"lastValue\": 0,\n"
          + "                        \"parameterNode\": null,\n"
          + "                        \"displayName\": \"PM1 count\",\n"
          + "                        \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                        \"parameterId\": 126,\n"
          + "                        \"firstUpdated\": \"2022-01-20T16:29:40+00:00\"\n"
          + "                    }\n"
          + "                ],\n"
          + "                \"lastUpdated\": \"2022-05-12T02:17:25+00:00\",\n"
          + "                \"firstUpdated\": \"2022-01-20T16:29:40+00:00\",\n"
          + "                \"measurements\": 962280\n"
          + "            }\n"
          + "        ]\n"
          + "    }       \n"
          + "]";
}
