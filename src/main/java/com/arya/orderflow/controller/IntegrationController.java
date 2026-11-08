package com.arya.orderflow.controller;

import com.arya.orderflow.client.TradingToolkitClient;
import com.arya.orderflow.dto.PortfolioResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/integration")
public class IntegrationController {

    private final TradingToolkitClient tradingToolkitClient;


    public IntegrationController(TradingToolkitClient tradingToolkitClient) {
        this.tradingToolkitClient = tradingToolkitClient;
    }

    @GetMapping("/python-health")
    public String health() {
        return tradingToolkitClient.healthCheck();
    }

    @GetMapping("/portfolio/{symbol}")
    public PortfolioResponse getPortfolioSymbol(@PathVariable String symbol) {
        return tradingToolkitClient.portfolioSymbol(symbol);
    }

//inbound http into java from tradingtoolkit
}
