package com.subaru.cv.vehicleservice.controller;

import com.subaru.cv.cafe.core.exception.CafeNotFoundException;
import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.api.DcmSoftwareApi.DcmSoftwareApiDelegate;
import com.subaru.cv.vehicleservice.service.DcmSoftwareService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests related to Dcm Software.
 */
@RestController
@Validated
@PreAuthorize("hasRole('ROLE_CV_MYSUBARU_USER')")
public class DcmSoftwareController implements DcmSoftwareApiDelegate {
  private final DcmSoftwareService dcmSoftwareService;

  /**
   * Instantiates a new Dcm software controller.
   *
   * @param dcmSoftwareService the dcm software service
   */
  public DcmSoftwareController(final DcmSoftwareService dcmSoftwareService) {
    this.dcmSoftwareService = dcmSoftwareService;
  }

  @Override
  public DcmSoftware getDcmSoftwareByDcmSoftware(final Long dcmSoftwareId) {
    return dcmSoftwareService.getDcmSoftwareByDcmSoftwareId(dcmSoftwareId)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm Software not found")
        );
  }

  @Override
  public DcmSoftware getDcmSoftwareByVin(final String vin) {
    return dcmSoftwareService.getDcmSoftwareByVin(vin)
        .orElseThrow(() ->
            new CafeNotFoundException("Dcm Software not found")
        );
  }
}
