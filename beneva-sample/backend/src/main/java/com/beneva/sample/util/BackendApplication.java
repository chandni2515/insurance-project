package com.beneva.sample.util;

import com.beneva.sample.claim.ClaimManagementApplication;
import com.beneva.sample.policy.PolicyBuilderApplication;
import org.springframework.boot.SpringApplication;

/**
 * Entry point to run backend modules from a single main class.
 */
public class BackendApplication {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Specify 'claim', 'policy' or 'all'");
            return;
        }
        switch (args[0]) {
            case "claim":
                SpringApplication.run(ClaimManagementApplication.class, args);
                break;
            case "policy":
                SpringApplication.run(PolicyBuilderApplication.class, args);
                break;
            case "all":
                SpringApplication.run(ClaimManagementApplication.class, args);
                SpringApplication.run(PolicyBuilderApplication.class, args);
                break;
            default:
                System.err.println("Unknown option: " + args[0]);
        }
    }
}
