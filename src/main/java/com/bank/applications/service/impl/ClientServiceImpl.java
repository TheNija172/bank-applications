package com.bank.applications.service.impl;

import com.bank.applications.dto.ClientCreateRequest;
import com.bank.applications.dto.ClientResponse;
import com.bank.applications.entity.Client;
import com.bank.applications.exception.ResourceNotFoundException;
import com.bank.applications.mapper.ClientMapper;
import com.bank.applications.repository.ClientJdbcRepository;
import com.bank.applications.repository.ClientRepository;
import com.bank.applications.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientJdbcRepository clientJdbcRepository;

    @Override
    public ClientResponse getById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        return clientMapper.toResponse(client);
    }

    @Override
    public List<ClientResponse> getAllJdbc() {
        return clientJdbcRepository.findAll()
                .stream()
                .map(clientMapper::toResponse)
                .toList();
    }
}
