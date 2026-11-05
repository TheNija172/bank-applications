package com.bank.applications.controller;

import com.bank.applications.dto.ClientResponse;
import com.bank.applications.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {

        return clientService.getById(id);
    }
}
