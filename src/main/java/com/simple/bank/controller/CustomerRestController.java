package com.simple.bank.controller;

import com.simple.bank.dto.CustomerDto;
import com.simple.bank.response.WsResponse;
import com.simple.bank.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(tags = {"Customer REST endpoints"})
public class CustomerRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);
    @Autowired
    private CustomerService customerService;

    /**
     * Find all customers from the system
     *
     * @return ResponseEntity of WsResponse with list of customer inside the object
     */
    @GetMapping(path = "/customers")
    @ApiOperation(value = "Find all Customers", notes = "Find All Customers with Address and Contact")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<WsResponse> getCustomer() {
        LOGGER.debug("Triggered CustomerController.getCustomer");
        return customerService.findAll();
    }

    /**
     * Add customer to system
     *
     * @param customerDto Customer Data to be added
     * @return Response Entity of WsResponse with success or failure message
     */
    @PostMapping(path = "/customers")
    @ApiOperation(value = "Add a Customer", notes = "Add customer to the system")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<WsResponse> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        LOGGER.debug("Triggered CustomerController.addCustomer");
        return customerService.addCustomer(customerDto);
    }
}
