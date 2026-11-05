package com.bank.applications.service;

import com.bank.applications.dto.ClientResponse;

import java.util.List;

public interface ClientService {

    public ClientResponse getById(Long id);

    public List<ClientResponse> getAllJdbc();
}
