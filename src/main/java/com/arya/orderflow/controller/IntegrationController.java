package com.arya.orderflow.controller;

import com.arya.orderflow.client.TradingToolkitClient;
import com.arya.orderflow.dto.PortfolioResponse;
import com.arya.orderflow.dto.PositionResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/integration")
public class IntegrationController {

    private final TradingToolkitClient tradingToolkitClient;


    public IntegrationController(TradingToolkitClient tradingToolkitClient) {
        this.tradingToolkitClient = tradingToolkitClient;
    }
    //constructor dependency injection

    @GetMapping("/python-health")
    public String health() {
        return tradingToolkitClient.healthCheck();
    }

    @GetMapping("/portfolio/{symbol}")
    public PortfolioResponse getPortfolioSymbol(@PathVariable String symbol) {
        return tradingToolkitClient.portfolioSymbol(symbol);
    }

    @GetMapping("/positions")
    public PositionResponse[] getPositions() {
        return tradingToolkitClient.positions();
    }
    // delegating the http comms to the client

//inbound http from tradingtoolkit to java
    // response comes back from python service and is handled in this controller
}
