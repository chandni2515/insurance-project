package com.beneva.sample.policy;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    private final PolicyRepository repository;
    private final KafkaTemplate<String, String> kafka;

    public PolicyService(PolicyRepository repository, KafkaTemplate<String, String> kafka) {
        this.repository = repository;
        this.kafka = kafka;
    }

    public Policy createPolicy(Policy p) {
        Policy saved = repository.save(p);
        kafka.send("policy-events", "Created policy " + saved.getId());
        return saved;
    }
}
