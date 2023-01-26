package com.example.amqartemis.controller;

import com.example.amqartemis.dto.ProductDTO;
import com.example.amqartemis.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supply")
@Slf4j
public class SupplyController {

    private final DispatcherService dispatcherService;

    @PostMapping
    public ProductDTO reportNewProduct(@RequestBody ProductDTO product){
        log.info("New product reported");
        dispatcherService.sendMessage(product);
        return product;
    }
}
