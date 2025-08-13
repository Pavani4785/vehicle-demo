package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.api.DcmGenerationApi.DcmGenerationApiDelegate;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.service.DcmGenerationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to Dcm Generations.
 */
@RestController
@Validated
@PreAuthorize("hasRole('ROLE_CV_MYSUBARU_USER')")
public class DcmGenerationController implements DcmGenerationApiDelegate {
  private final DcmGenerationService dcmGenerationService;

  /**
   * Instantiates a new Dcm generation controller.
   *
   * @param dcmGenerationService the dcm generation service
   */
  public DcmGenerationController(final DcmGenerationService dcmGenerationService) {
    this.dcmGenerationService = dcmGenerationService;
  }

  @Override
  public DcmGeneration getDcmGenerationByDcmGeneration(final String dcmGeneration) {
    var market = MarketType.SOA_USA;
    return dcmGenerationService.getDcmGenerationByDcmGeneration(dcmGeneration, market)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm Generation not found")
        );
  }

  @Override
  public DcmGeneration getDcmGenerationByVin(final String vin) {
    return dcmGenerationService.getDcmGenerationByVin(vin)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm Generation not found")
        );
  }
}
