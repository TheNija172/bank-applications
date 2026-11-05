package com.bank.applications.controller;

import com.bank.applications.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final ApplicationService applicationService;

    @GetMapping("/")
    public String getApplications(Model model) {

        model.addAttribute("applications", applicationService.getAll());

        return "applications";
    }
}