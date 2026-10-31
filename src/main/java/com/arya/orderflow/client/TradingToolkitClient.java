package com.arya.orderflow.client;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.stereotype.Service;


// Outbound http client to communicate with my trading toolkit (external service)
@Component
public class TradingToolkitClient {

    private final RestClient restClient;

    // outbound http client
    // final makes dependency immutable

    public TradingToolkitClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://127.0.0.1:8000").build();
    }

    public String healthCheck() {
      return restClient
              .get()
              .uri("/health")
              .retrieve()
              .body(String.class);

      //restclient -> http get -> append to base url -> send request and retrieve response
        //give me the response body as a String
    };
    // this says a trading toolkit(constructor) cannot exist without a rest client
    // the builder is only needed for the construction to configure and create teh restclient obj



}
