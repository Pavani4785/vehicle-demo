package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.GlobalModel;
import com.subaru.cv.vehicleservice.domain.TeleGlobalModelMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelSpecMstEntity;
import org.springframework.stereotype.Component;

@Component
public class GlobalModelMapper {

  /**
   * Maps {@link TeleGlobalModelMstEntity} and {@link TeleLocalModelSpecMstEntity} to a {@link GlobalModel} DTO.
   * @param teleGlobalModelMstEntity the {@link TeleGlobalModelMstEntity} entity
   * @param teleLocalModelSpecMstEntity the {@link TeleLocalModelSpecMstEntity} entity
   * @return the {@link GlobalModel} DTO
   */
  public GlobalModel toDto(
      final TeleGlobalModelMstEntity teleGlobalModelMstEntity,
      final TeleLocalModelSpecMstEntity teleLocalModelSpecMstEntity
  ) {
    var globalModel = new GlobalModel();

    globalModel.setGlobalModelCode(teleGlobalModelMstEntity.getGlobalModelCode());
    globalModel.setGlobalOptionCode(teleGlobalModelMstEntity.getGlobalOptionCode());
    globalModel.setGlobalSpecificationCode(teleGlobalModelMstEntity.getGlobalSpecCode());
    globalModel.setGlobalExteriorColorCode(teleGlobalModelMstEntity.getGlobalExteriorColor());
    globalModel.setGlobalInteriorColorCode(teleGlobalModelMstEntity.getGlobalInteriorColor());

    globalModel.setSpecCode(teleLocalModelSpecMstEntity.getSpecCode());
    globalModel.setSpecCertificationVerbiage(teleLocalModelSpecMstEntity.getSpecCertificationVerbiage());

    return globalModel;
  }

}
