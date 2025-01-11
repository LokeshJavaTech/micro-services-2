package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * "value" within the annotation is same with Logical name that Cards microservice is registering with the Eureka Server,
 *  it exists in spring.application.name in Cards Micro Service
 *
 * - Feign Client will talk with Eureka Server and ask for MS registered as "cards",
 * - Get list of IP Address from Eureka Server
 * - Do client side load balancing and select 1 IP address of Card MS to connect with
 * - Send request to that particular Card MS and get response
 * */

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetailsFromCardsMicroService(@RequestParam String mobileNumber);

}
