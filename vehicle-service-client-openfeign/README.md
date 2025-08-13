# Vehicle Service OpenFeign Client

A Java client generator using Spring Boot and Feign.

* Provides a feign client for backend-to-backend communications with the reference-microservice
* This module looks empty at first glance, but a feign client will be built from the OpenAPI spec.
* This module is meant to be used as a dependency

## Usage

Add the following to your build.gradle:

```kotlin
dependencies {
  implementation("com.subaru.cv.vehicle:vehicle-service-client-openfeign:$vehicleServiceClientOpenfeignVersion")
}
```

Add `@EnableFeignClients(basePackages = "com.subaru.cv.api")` to your main application class or a configuration class.

```java
@SpringBootApplication
@EnableFeignClients(basePackages = "com.subaru.cv.api")
public class FeignClientDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeignClientDemoApplication.class, args);
  }

}
```

Then you should be able to use the client in your services:

```java

@Service
public class VehicleService {
  private final VehicleClient vehicleClient;

  public VehicleService(final VehicleClient vehicleClient) {
    this.vehicleClient = vehicleClient;
  }

  public Vehicle getVehicleByVin(final String vin) {
    return vehicleClient.getVehicleByVin(vin);
  }
}
```
