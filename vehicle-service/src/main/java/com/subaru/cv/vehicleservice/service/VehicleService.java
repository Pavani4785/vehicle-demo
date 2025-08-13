package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.domain.TeleVinMasterEntity;
import com.subaru.cv.vehicleservice.mapper.VehicleMapper;
import com.subaru.cv.vehicleservice.repository.TeleVinMasterEntityRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving vehicle information.
 */
@Service
public class VehicleService {

  private final TeleVinMasterEntityRepository teleVinMasterEntityRepository;
  private final VehicleMapper vehicleMapper;

  /**
   * Instantiates a new {@link VehicleService}.
   *
   * @param teleVinMasterEntityRepository Repository for {@link TeleVinMasterEntity} entities.
   * @param vehicleMapper                 The mapper.
   */
  public VehicleService(
      final TeleVinMasterEntityRepository teleVinMasterEntityRepository,
      final VehicleMapper vehicleMapper
  ) {
    this.teleVinMasterEntityRepository = teleVinMasterEntityRepository;
    this.vehicleMapper = vehicleMapper;
  }

  /**
   * Retrieves a set of {@link Vehicle vehicles} given their vins.
   *
   * @param vins The vins of the vehicles to retrieve
   * @return a set of {@link Vehicle} objects corresponding to the provided VINs,
   *     or an empty set if no VINs are provided or no matches are found
   */
  public Set<Vehicle> getVehiclesByVins(final Collection<String> vins) {
    return teleVinMasterEntityRepository
        .findByVinIn(vins)
        .stream()
        .map(vehicleMapper::toDto)
        .collect(Collectors.toUnmodifiableSet());
  }

  /**
   * Retrieves a single {@link Vehicle} by its vin.
   *
   * @param vin The vin of the vehicle to retrieve
   * @return the {@link Vehicle} corresponding to the provided vin
   */
  public Optional<Vehicle> getVehicleByVin(final String vin) {
    return teleVinMasterEntityRepository
        .findByVinFilterActiveFeatures(vin)
        .map(vehicleMapper::toDto);
  }
}
