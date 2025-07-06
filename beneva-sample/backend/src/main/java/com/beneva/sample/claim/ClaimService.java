package com.beneva.sample.claim;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {
    private final ClaimRepository repository;
    private final KafkaTemplate<String, String> kafka;

    public ClaimService(ClaimRepository repository, KafkaTemplate<String, String> kafka) {
        this.repository = repository;
        this.kafka = kafka;
    }

    public Claim fileClaim(Claim c) {
        Claim saved = repository.save(c);
        kafka.send("claim-events", "Filed claim " + saved.getId());
        return saved;
    }
}
