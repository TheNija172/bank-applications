package com.bank.applications.service;

import com.bank.applications.dto.ApplicationCreateRequest;
import com.bank.applications.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    public ApplicationResponse create(ApplicationCreateRequest request);

    public List<ApplicationResponse> getAll();

    public Integer calculateScore(Long id);
}
