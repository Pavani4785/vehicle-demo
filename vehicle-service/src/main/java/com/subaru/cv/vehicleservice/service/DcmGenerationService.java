package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.mapper.DcmGenerationMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmGenMstEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service for managing DCM (Data Communication Module) generation information.
 */
@Service
public class DcmGenerationService {
  private final TeleDcmGenMstEntityRepository teleDcmGenMstEntityRepository;
  private final DcmGenerationMapper dcmGenerationMapper;

  /**
   * Instantiates a new Dcm Generation service.
   *
   * @param teleDcmGenMstEntityRepository the teleDcmGenMst entity repository
   * @param dcmGenerationMapper           the dcm generation mapper
   */
  public DcmGenerationService(
      final TeleDcmGenMstEntityRepository teleDcmGenMstEntityRepository,
      final DcmGenerationMapper dcmGenerationMapper
  ) {
    this.teleDcmGenMstEntityRepository = teleDcmGenMstEntityRepository;
    this.dcmGenerationMapper = dcmGenerationMapper;
  }

  /**
   * Gets {@link DcmGeneration} by dcm generation string and market.
   * @param dcmGeneration the dcm generation string
   * @param market the {@link MarketType} to filter by
   * @return the {@link DcmGeneration} if found, otherwise empty
   */
  public Optional<DcmGeneration> getDcmGenerationByDcmGeneration(
      final String dcmGeneration,
      final MarketType market
  ) {
    return teleDcmGenMstEntityRepository
        .findByDcmGenAndMarketId(dcmGeneration, market.getMarketId())
        .map(dcmGenerationMapper::toDto);
  }

  /**
   * Gets dcm generation by vin.
   *
   * @param vin the vin
   * @return the dcm generation by vin
   */
  public Optional<DcmGeneration> getDcmGenerationByVin(final String vin) {
    return teleDcmGenMstEntityRepository
        .findByVin(vin)
        .map(dcmGenerationMapper::toDto);
  }
}
