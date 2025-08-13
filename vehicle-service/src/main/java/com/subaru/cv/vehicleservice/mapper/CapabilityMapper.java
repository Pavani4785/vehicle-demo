package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.BaseCapability;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for going from a {@link TeleFeaturesMstEntity} to a {@link BaseCapability} DTO and vice versa.
 */
@Component
public class CapabilityMapper {
  /**
   * Maps {@link TeleFeaturesMstEntity} to {@link BaseCapability} DTO.
   *
   * @param teleFeaturesMstEntity the TeleFeatures entity
   * @return the {@link BaseCapability}
   */
  public BaseCapability toDto(final TeleFeaturesMstEntity teleFeaturesMstEntity) {
    var baseCapability = new BaseCapability();
    baseCapability.setCapabilityCode(teleFeaturesMstEntity.getFeatureCode());
    baseCapability.setRemoteFlag(teleFeaturesMstEntity.isRemoteFlg());
    baseCapability.setDescription(teleFeaturesMstEntity.getFeatureDesc());
    baseCapability.setDeliveryChecklistFlag(teleFeaturesMstEntity.getDeliveryChecklistFlg());
    baseCapability.setMonroneyLabelFlag(teleFeaturesMstEntity.getMonroneyLabelFlg());
    return baseCapability;
  }

  /**
   * Maps {@link BaseCapability} to entity {@link TeleFeaturesMstEntity} entity.
   *
   * @param capability the capability
   * @return the {@link TeleFeaturesMstEntity} entity
   */
  public TeleFeaturesMstEntity toEntity(final BaseCapability capability) {
    var teleFeaturesMstEntity = new TeleFeaturesMstEntity();
    teleFeaturesMstEntity.setFeatureCode(capability.getCapabilityCode());
    teleFeaturesMstEntity.setRemoteFlg(capability.getRemoteFlag());
    teleFeaturesMstEntity.setFeatureDesc(capability.getDescription());
    teleFeaturesMstEntity.setDeliveryChecklistFlg(capability.getDeliveryChecklistFlag());
    teleFeaturesMstEntity.setMonroneyLabelFlg(capability.getMonroneyLabelFlag());
    return teleFeaturesMstEntity;
  }

}
