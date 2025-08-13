package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.domain.TeleVinMasterEntity;
import com.subaru.cv.vehicleservice.mapper.VehicleMapper;
import com.subaru.cv.vehicleservice.repository.TeleVinMasterEntityRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The type Vehicle service test.
 */
@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

  @InjectMocks
  private VehicleService vehicleService;

  @Mock
  private TeleVinMasterEntityRepository vehicleRepository;

  @Mock
  private VehicleMapper vehicleMapper;

  @Test
  void test_getVehiclesByVins_validVins_correctAmountOfVehicles() {
    // Arrange
    int listSize = Instancio.gen()
        .ints()
        .range(1, 10)
        .get();

    var vins = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .list(listSize)
        .stream()
        .collect(Collectors.toUnmodifiableSet());

    var vehicleEntities = Instancio.ofSet(TeleVinMasterEntity.class)
        .size(listSize)
        .create();

    when(vehicleRepository.findByVinIn(vins))
        .thenReturn(vehicleEntities);

    when(vehicleMapper.toDto(any(TeleVinMasterEntity.class)))
        .thenAnswer((invocation) ->
            Instancio.create(Vehicle.class)
        );

    // Act
    var vehicles = vehicleService.getVehiclesByVins(vins);

    // Assert
    assertEquals(listSize, vehicles.size());
  }

  @Test
  void test_getVehicleByVin_validVin_vehicle() {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .get();

    var vehicleEntity = Instancio.create(TeleVinMasterEntity.class);
    var vehicle = Instancio.create(Vehicle.class);

    when(vehicleRepository.findByVinFilterActiveFeatures(vin))
        .thenReturn(Optional.of(vehicleEntity));

    when(vehicleMapper.toDto(vehicleEntity))
        .thenReturn(vehicle);

    // Act
    var result = vehicleService.getVehicleByVin(vin);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(vehicle, result.get());
  }
}
