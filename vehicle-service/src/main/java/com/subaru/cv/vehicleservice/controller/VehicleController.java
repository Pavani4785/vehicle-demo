package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.GetVehiclesByVinsRequest;
import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.api.VehicleApi.VehicleApiDelegate;
import com.subaru.cv.vehicleservice.service.VehicleService;
import java.util.Set;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to vehicles.
 */
@RestController
@Validated
@PreAuthorize("hasRole('ROLE_CV_MYSUBARU_USER')")
public class VehicleController implements VehicleApiDelegate {
  private final VehicleService vehicleService;

  /**
   * Instantiates a new Vehicle controller.
   *
   * @param vehicleService the vehicle service
   */
  public VehicleController(final VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @Override
  public Vehicle getVehicleByVin(final String vin) {
    return vehicleService.getVehicleByVin(vin)
        .orElseThrow(() ->
            new CafeNotFoundException("Vehicle not found")
        );
  }

  @Override
  public Set<Vehicle> getVehiclesByVins(
      final GetVehiclesByVinsRequest getVehiclesByVinsRequest
  ) {
    return vehicleService.getVehiclesByVins(
        getVehiclesByVinsRequest.getVins()
    );
  }

}
