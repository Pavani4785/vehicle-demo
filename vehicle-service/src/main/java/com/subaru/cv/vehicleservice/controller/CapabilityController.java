package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.BaseCapability;
import com.subaru.cv.vehicleservice.api.CapabilityApi.CapabilityApiDelegate;
import com.subaru.cv.vehicleservice.service.CapabilityService;
import java.util.Set;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to vehicle capabilities.
 */
@RestController
@Validated
@PreAuthorize("hasRole('ROLE_CV_MYSUBARU_USER')")
public class CapabilityController implements CapabilityApiDelegate {
  private final CapabilityService capabilityService;

  /**
   * Instantiates a new Capability controller.
   *
   * @param capabilityService the capability service
   */
  public CapabilityController(final CapabilityService capabilityService) {
    this.capabilityService = capabilityService;
  }

  @Override
  public BaseCapability getCapability(final String capabilityCode) {
    return capabilityService.getBaseCapability(capabilityCode)
        .orElseThrow(() ->
            new CafeNotFoundException("Capability not found")
        );
  }

  @Override
  public Set<BaseCapability> listCapabilities() {
    return capabilityService.getBaseCapabilities();
  }
}
