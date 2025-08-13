package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.domain.TeleDcmGenMstEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleDcmGenMstEntity} and {@link DcmGeneration} DTO.
 */
@Component
public class DcmGenerationMapper {

  private final MarketMapper marketMapper;

  /**
   * Constructor for DcmGenerationMapper.
   *
   * @param marketMapper the {@link MarketMapper} to use for mapping market information
   */
  public DcmGenerationMapper(final MarketMapper marketMapper) {
    this.marketMapper = marketMapper;
  }

  /**
   * Maps {@link TeleDcmGenMstEntity} to {@link DcmGeneration} DTO.
   *
   * @param teleDcmGenMstEntity the DCM generation entity
   * @return the {@link DcmGeneration} DTO
   */
  public DcmGeneration toDto(final TeleDcmGenMstEntity teleDcmGenMstEntity) {
    var dcmGeneration = new DcmGeneration();
    dcmGeneration.setDcmGeneration(teleDcmGenMstEntity.getDcmGen());
    dcmGeneration.setTspCode(teleDcmGenMstEntity.getTspCode());

    dcmGeneration.setMarketCode(
        marketMapper.toMarketCode(
            teleDcmGenMstEntity.getMarket()
        )
    );

    return dcmGeneration;
  }
}
