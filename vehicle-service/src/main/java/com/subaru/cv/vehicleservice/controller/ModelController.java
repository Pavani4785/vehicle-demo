package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.Capability;
import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.api.ModelApi.ModelApiDelegate;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.service.CapabilityService;
import com.subaru.cv.vehicleservice.service.ModelService;
import java.util.Set;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to vehicle models.
 */
@RestController
@Validated
public class ModelController implements ModelApiDelegate {
  private final ModelService modelService;
  private final CapabilityService capabilityService;

  /**
   * Instantiates a new Model controller.
   *
   * @param modelService      the {@link ModelService}
   * @param capabilityService the {@link CapabilityService}
   */
  public ModelController(
      final ModelService modelService,
      final CapabilityService capabilityService
  ) {
    this.modelService = modelService;
    this.capabilityService = capabilityService;
  }

  @Override
  public Model getModelByModelKey(final String modelKey) {
    var market = MarketType.SOA_USA;
    return modelService.getModelByModelKey(modelKey, market)
        .orElseThrow(() ->
            new CafeNotFoundException("Model not found")
        );
  }

  @Override
  public Model getModelByVin(final String vin) {
    return modelService.getModelByVin(vin)
        .orElseThrow(() ->
            new CafeNotFoundException("Model not found for vin: " + vin)
        );
  }

  @Override
  public Set<Capability> listModelCapabilities(final String modelKey) {
    var market = MarketType.SOA_USA;
    return capabilityService.getCapabilities(modelKey, market);
  }

  @Override
  public Set<String> getCapabilityCodesByModelKey(
      final String modelKey,
      final Boolean includeInactive
  ) {
    var market = MarketType.SOA_USA;
    return capabilityService.getCapabilityCodes(modelKey, market, includeInactive);
  }
}
