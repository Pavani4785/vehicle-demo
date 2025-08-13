package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import com.subaru.cv.vehicleservice.mapper.DcmMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmMstEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service for managing DCM (Data Communication Module) information.
 */
@Service
public class DcmService {
  private final TeleDcmMstEntityRepository teleDcmMstEntityRepository;
  private final DcmMapper dcmMapper;

  /**
   * Constructs a new {@link DcmService}.
   *
   * @param teleDcmMstEntityRepository Repository for {@link TeleDcmMstEntity}.
   * @param dcmMapper Mapper for converting DCM entities to DTOs.
   */
  public DcmService(
      final TeleDcmMstEntityRepository teleDcmMstEntityRepository,
      final DcmMapper dcmMapper
  ) {
    this.teleDcmMstEntityRepository = teleDcmMstEntityRepository;
    this.dcmMapper = dcmMapper;
  }

  /**
   * Retrieves a {@link Dcm} by its ID.
   *
   * @param dcmId The ID of the DCM to retrieve.
   * @return The {@link Dcm}.
   */
  public Optional<Dcm> getDcmByDcmId(final long dcmId) {
    return teleDcmMstEntityRepository
        .findById(dcmId)
        .map(dcmMapper::toDto);
  }

  /**
   * Retrieves a {@link Dcm} by vin.
   *
   * @param vin The vin to search by.
   * @return The {@link Dcm}.
   */
  public Optional<Dcm> getDcmByVin(final String vin) {
    return teleDcmMstEntityRepository
        .findByVin(vin)
        .map(dcmMapper::toDto);
  }
}
