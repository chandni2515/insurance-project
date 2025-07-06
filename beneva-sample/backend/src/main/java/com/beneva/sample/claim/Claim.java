package com.beneva.sample.claim;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Claim {
    @Id
    private String id;
    private String policyNumber;
    private String description;

    public Claim() {}

    public Claim(String policyNumber, String description) {
        this.policyNumber = policyNumber;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
