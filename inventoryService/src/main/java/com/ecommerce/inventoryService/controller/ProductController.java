package com.ecommerce.inventoryService.controller;

import com.ecommerce.inventoryService.Clients.FeignClientInt;
import com.ecommerce.inventoryService.dto.ProductDto;
import com.ecommerce.inventoryService.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;

    @Autowired
    private FeignClientInt feignClientInt;

    @GetMapping(path = "/DiscoverOrders")
//    @Retry(name="InventoryRetry",fallbackMethod = "HandleRetry")
//    @RateLimiter(name="InventoryRateLimiter",fallbackMethod = "HandleRetry")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "HandleRetry")
    public String DiscoverOrders(){
        System.out.println("called");
//        System.out.println("discoveryClient.getInstances "+discoveryClient.getInstances("ORDER-SERVICE") );
//       ServiceInstance instance=discoveryClient.getInstances("ORDER-SERVICE").getFirst();
//
//        System.out.println("instance.getUri() "+instance.getUri());
//       String response=restClient.get().uri(instance.getUri()+"/orders/core/ServiceDiscoveryTest").
//               retrieve().
//               body(String.class);
//       return response;
        return feignClientInt.TestDiscovery();

    }

    public String HandleRetry(Throwable throwable){
        System.out.println("Retry Failed due to "+throwable.getMessage());
        return "Retry Failed";
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory() {
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id) {
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }


}
