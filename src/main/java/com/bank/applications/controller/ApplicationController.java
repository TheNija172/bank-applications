package com.bank.applications.controller;

import com.bank.applications.dto.ApplicationCreateRequest;
import com.bank.applications.dto.ApplicationResponse;
import com.bank.applications.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationResponse create (@Valid @RequestBody ApplicationCreateRequest request) {

        return applicationService.create(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAll() {

        return applicationService.getAll();
    }

    @PostMapping("/{id}/calculate")
    public Integer calculate(@PathVariable Long id) {

        return applicationService.calculateScore(id);
    }
}
