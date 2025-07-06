package com.beneva.sample.claim;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {
    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    @PostMapping
    public Claim create(@RequestBody Claim claim) {
        return service.fileClaim(claim);
    }
}
