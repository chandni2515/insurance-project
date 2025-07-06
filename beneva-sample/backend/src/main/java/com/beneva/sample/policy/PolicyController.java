package com.beneva.sample.policy;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @PostMapping
    public Policy create(@RequestBody Policy policy) {
        return service.createPolicy(policy);
    }
}
