package com.ecommerce.inventoryService.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="ORDER-SERVICE")
public interface FeignClientInt {

    @GetMapping(path ="/orders/core/ServiceDiscoveryTest")
    String TestDiscovery();
}
