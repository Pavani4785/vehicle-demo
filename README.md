# Vehicle Service

The Connected Vehicle Vehicle Service.

This project leverages CAFE, a CV framework. Find documentation for it [here](https://subaruofamerica.atlassian.net/wiki/spaces/CVIT/pages/1024229377/CAFE+-+Common+Application+Framework+E)

## Development Environment

### 1. Use the CV gradle distribution

* This project uses a custom gradle distribution. Find it on confluence
  [here](https://subaruofamerica.atlassian.net/wiki/spaces/CVIT/pages/1551073350/IntelliJ+Gradle+Setup)

### 2. Note on AWS-Specific Dependencies

* This project leverages AWS Secrets Manager to pull in secrets.
  To use this, you need to set up your AWS
  credentials: [AWS CLI Configuration](https://subaruofamerica.atlassian.net/wiki/spaces/CVIT/pages/1024131174/AWS+CLI+Setup+Guide)
* If you are using IntelliJ, please leverage the AWS Core and AWS Toolkit plugins to help with AWS
  development: [AWS Core & AWS Toolkit Plugins](https://subaruofamerica.atlassian.net/wiki/spaces/CVIT/pages/1385889837/IntelliJ+AWS+Core+and+Toolkit+Setup)
* Proxyman does not play nice with AWS SDKs. If you get PKIX issues, either close Proxyman or install the AWS
  Certificates to Proxyman (not tested, but it should be possible).

## Log into AWS before running locally

This repository reaches out to AWS to get database credentials from Secrets Manager. To run locally, you will need to
log into AWS through either:

* the AWS Toolkit plugin in IntelliJ if running from the IDE
* through the AWS CLI if running from the terminal

## Build

```bash
gradle build
```

## Run locally

Run the built JAR file as you would any other Spring Boot application. Ideally leverage your IDE to assist.

Ensure you have the `local` Spring profile set either via IDE
or supplying `spring.profiles.active=local` as an environment variable.

If you get an error about an AWS secret not being found, ensure you have logged into AWS as described above.

### Swagger UI
To view the Swagger UI for this service, you can access it at the following URL: http://localhost:8080/openapi/v3/swagger-ui/index.html.
Springdoc generated API documentation is available at http://localhost:8080/openapi/v3/api-docs or http://localhost:8080/openapi/v3/api-docs.yaml.

## testing

API tests can be found in the `api-testing` module. These tests leverage the[IntelliJ IDEA HTTP Client](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html).
They will run using an environment you set, and will ensure that each endpoint returns a 200 OK when reached.

## Publish locally

```bash
gradle publishToMavenLocal
```

## Deploy:

This project is published and deployed via GitHub Actions. The workflows are defined in the `.github/workflows`
directory.

## Modules:

### vehicle-service

* The controller layer, service layer, and data access layers are defined in this module.
* The api specification is driven from the openApi spec at: `$root/src/main/resources/`
  * An api delegate pattern is generated
  * The delegate is implemented in code

### vehicle-service-domain

* This module will appear empty at first glance. It holds the domain model objects generated from the OpenAPI spec.
* This module is intended for use internally in this project

## Clients

This project has two example client generator modules.

### vehicle-service-client-redux

A TypeScript client using React, Redux, and RTK Query.

See the README within the `vehicle-service-client-redux` module for more details.

### vehicle-service-client-openfeign

A Java client using Spring Boot and Feign.

See the README within the `vehicle-service-client-openfeign` module for more details.
