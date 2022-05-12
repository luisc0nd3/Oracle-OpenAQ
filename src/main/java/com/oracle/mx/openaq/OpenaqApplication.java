package com.oracle.mx.openaq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OpenaqApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenaqApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(16000);
    factory.setReadTimeout(16000);
    return new RestTemplate(factory);
  }
}
