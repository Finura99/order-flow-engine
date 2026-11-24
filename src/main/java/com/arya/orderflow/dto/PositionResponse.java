package com.arya.orderflow.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PositionResponse(
        String symbol,

        @JsonProperty("net_quantity")
        BigDecimal netQuantity,

        @JsonProperty("market_price")
        BigDecimal marketPrice,

        @JsonProperty("exposure")
        BigDecimal exposure
) {}