# Beneva Sample Project

This sample project demonstrates a simplified version of the **Policy Builder** and **Claim Management** modules using technologies listed in the resume.

## Backend
* **Spring Boot 2.5** (Java 11)
* Oracle (JPA) and MongoDB
* Kafka for event communication
* Dockerfile for containerization
* Jenkinsfile for CI

## Frontend
* React 17
* Webpack configuration with Babel
* Dockerfile for containerization

### Running the Frontend
1. Install Node.js 14 or later.
2. From the `frontend` folder run `npm install` to install React, Webpack and
   Babel dependencies.
3. Start the development server with `npm start`.

## Infrastructure
* `docker-compose.yml` for local setup
* Kubernetes deployment manifest

Logging and monitoring (Splunk, Dynatrace) would be integrated via standard logging frameworks and agents.
