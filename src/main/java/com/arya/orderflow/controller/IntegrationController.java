package com.arya.orderflow.controller;

import com.arya.orderflow.client.TradingToolkitClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController {

    private final TradingToolkitClient tradingToolkitClient;


    public IntegrationController(TradingToolkitClient tradingToolkitClient) {
        this.tradingToolkitClient = tradingToolkitClient;
    }

    @RequestMapping("/integration")
    
}
