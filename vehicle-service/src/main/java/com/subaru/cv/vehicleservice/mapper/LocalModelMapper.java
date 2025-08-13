package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleLocalModelMstEntity} and {@link Model} DTO.
 */
@Component
public class LocalModelMapper {

  private final MarketMapper marketMapper;

  /**
   * Constructs a LocalModelMapper.
   *
   * @param marketMapper the {@link MarketMapper} to use for mapping market codes
   */
  public LocalModelMapper(final MarketMapper marketMapper) {
    this.marketMapper = marketMapper;
  }

  /**
   * Maps {@link TeleLocalModelMstEntity} to {@link Model} DTO.
   *
   * @param teleLocalModelMstEntity the TeleLocalModel entity
   * @return the {@link Model}
   */
  public Model toDto(final TeleLocalModelMstEntity teleLocalModelMstEntity) {
    var model = new Model();

    model.setModelYear(teleLocalModelMstEntity.getModelYear());
    model.setModelCode(teleLocalModelMstEntity.getModelCode());
    model.setOptionCode(teleLocalModelMstEntity.getOptionCode());

    model.setDcmGeneration(
        teleLocalModelMstEntity
            .getDcmGenMaster()
            .getDcmGen()
    );

    model.setModelDescription(teleLocalModelMstEntity.getModelDesc());
    model.setBodyStyle(teleLocalModelMstEntity.getBodyStyle());
    model.setTransmission(teleLocalModelMstEntity.getTransmission());
    model.setTurbo(teleLocalModelMstEntity.getTurbo());
    model.setDriveTrain(teleLocalModelMstEntity.getDriveTrain());
    model.setEngineSize(teleLocalModelMstEntity.getEngineSize());
    model.setModelTrim(teleLocalModelMstEntity.getTrim());
    model.setModelTrimDescription(teleLocalModelMstEntity.getModelTrimDesc());
    model.setModelKey(teleLocalModelMstEntity.getLocalModelKey());
    model.setMotivePower(teleLocalModelMstEntity.getMotivePower());

    model.setMarketCode(
        marketMapper.toMarketCode(
            teleLocalModelMstEntity.getMarket()
        )
    );

    var headUnitGen = teleLocalModelMstEntity.getHeadUnitGenMaster();
    model.setHeadUnitGeneration(headUnitGen.getHeadUnitGen());
    model.setHeadUnitProviderCode(headUnitGen.getHuProviderCode());

    return model;
  }

}
