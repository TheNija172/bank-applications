package com.bank.applications.service.impl;

import com.bank.applications.dto.ApplicationCreateRequest;
import com.bank.applications.dto.ApplicationResponse;
import com.bank.applications.entity.Application;
import com.bank.applications.entity.Client;
import com.bank.applications.mapper.ApplicationMapper;
import com.bank.applications.repository.ApplicationRepository;
import com.bank.applications.repository.ClientRepository;
import com.bank.applications.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ClientRepository clientRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponse create(ApplicationCreateRequest request) {

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Application application = applicationMapper.toEntity(request);

        application.setClient(client);

        application.setStatus("NEW");

        Application savedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }

    @Override
    public List<ApplicationResponse> getAll() {

        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toResponse)
                .toList();
    }

    @Override
    public Integer calculateScore(Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        BigDecimal amount = application.getAmount();

        int score;

        if (amount.compareTo(BigDecimal.valueOf(100000)) < 0) {
            score = 10;
        } else if (amount.compareTo(BigDecimal.valueOf(100000)) >= 0 &&
            amount.compareTo(BigDecimal.valueOf(500000)) <= 0) {
            score = 50;
        } else {
            score = 100;
        }

        application.setScore(score);

        applicationRepository.save(application);

        return score;
    }
}
