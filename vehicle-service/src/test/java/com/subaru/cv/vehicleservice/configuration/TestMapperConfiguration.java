package com.subaru.cv.vehicleservice.configuration;

import com.subaru.cv.vehicleservice.mapper.VehicleMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Test configuration for mappers.
 * Wires up all the mappers for unit tests.
 */
@TestConfiguration
@ComponentScan(basePackageClasses = VehicleMapper.class)
public class TestMapperConfiguration {

}
