package com.arya.orderflow.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PortfolioResponse(
        String symbol,

        @JsonProperty("total_quantity")
        BigDecimal totalQuantity,

        @JsonProperty("average_price")
        BigDecimal averagePrice,

        @JsonProperty("total_value")
        BigDecimal totalValue
) {

}
// records automatically gives you a constructor and accessors

// this is when the data that comes back from our external service is converted into a domain object for this service to understand
//rather than propagating it as a string
