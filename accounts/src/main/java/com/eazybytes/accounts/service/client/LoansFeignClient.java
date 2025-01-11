package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* "value" within the annotation is same with Logical name that Loans microservice is registering with the Eureka Server,
*  it exists in spring.application.name in Loans Micro Service
*
* - Feign Client will talk with Eureka Server and ask for MS registered as "loans",
* - Get list of IP Address from Eureka Server
* - Do client side load balancing and select 1 IP address of Loan MS to connect with
* - Send request to that particular Loan MS and get response
* */

@FeignClient(value="loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetailsFromLoansMicroService(@RequestParam String mobileNumber);

}
