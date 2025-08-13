package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.domain.TeleDcmSoftwareMstEntity;
import com.subaru.cv.vehicleservice.mapper.DcmSoftwareMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmSoftwareMstEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving DCM software information.
 */
@Service
public class DcmSoftwareService {
  private final TeleDcmSoftwareMstEntityRepository teleDcmSoftwareMstEntityRepository;
  private final DcmSoftwareMapper dcmSoftwareMapper;

  /**
   * Instantiates a new {@link DcmSoftwareService}.
   *
   * @param teleDcmSoftwareMstEntityRepository Repository for {@link TeleDcmSoftwareMstEntity}.
   * @param dcmSoftwareMapper                  The mapper.
   */
  public DcmSoftwareService(
      final TeleDcmSoftwareMstEntityRepository teleDcmSoftwareMstEntityRepository,
      final DcmSoftwareMapper dcmSoftwareMapper
  ) {
    this.teleDcmSoftwareMstEntityRepository = teleDcmSoftwareMstEntityRepository;
    this.dcmSoftwareMapper = dcmSoftwareMapper;
  }

  /**
   * Retrieves {@link com.subaru.cv.model.DcmSoftware} information by its ID.
   *
   * @param dcmSoftwareId The ID of the {@link TeleDcmSoftwareMstEntity} to retrieve.
   * @return The {@link com.subaru.cv.model.DcmSoftware}
   */
  public Optional<DcmSoftware> getDcmSoftwareByDcmSoftwareId(final long dcmSoftwareId) {
    return teleDcmSoftwareMstEntityRepository
        .findById(dcmSoftwareId)
        .map(dcmSoftwareMapper::toDto);
  }

  /**
   * Retrieves {@link DcmSoftware} information by vin.
   *
   * @param vin The vin to search for.
   * @return The {@link DcmSoftware}
   */
  public Optional<DcmSoftware> getDcmSoftwareByVin(final String vin) {
    return teleDcmSoftwareMstEntityRepository
        .findByVin(vin)
        .map(dcmSoftwareMapper::toDto);
  }
}
