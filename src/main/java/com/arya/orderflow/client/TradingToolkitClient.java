package com.arya.orderflow.client;


import com.arya.orderflow.dto.PortfolioResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


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

    public PortfolioResponse portfolioSymbol(String symbol) {
        return restClient
                .get()
                .uri("/portfolio/{symbol}", symbol)
                .retrieve()
                .body(PortfolioResponse.class);
    }
    // this says a trading toolkit(constructor) cannot exist without a rest client
    // the builder is only needed for the construction to configure and create the restclient obj
}

//outbound HTTP from java to tradingtoolkit
