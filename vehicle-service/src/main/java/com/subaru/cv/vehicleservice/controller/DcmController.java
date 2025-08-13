package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.api.DcmApi.DcmApiDelegate;
import com.subaru.cv.vehicleservice.service.DcmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to Data Control Modules (DCMs).
 */
@RestController
@Validated
@PreAuthorize("hasRole('ROLE_CV_MYSUBARU_USER')")
public class DcmController implements DcmApiDelegate {
  private final DcmService dcmService;

  /**
   * Instantiates a new Dcm controller.
   *
   * @param dcmService the dcm controller
   */
  public DcmController(final DcmService dcmService) {
    this.dcmService = dcmService;
  }

  @Override
  public Dcm getDcmByDcmId(final Long dcmId) {
    return dcmService.getDcmByDcmId(dcmId)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm not found")
        );
  }

  @Override
  public Dcm getDcmByVin(final String vin) {
    return dcmService.getDcmByVin(vin)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm not found")
        );
  }
}
