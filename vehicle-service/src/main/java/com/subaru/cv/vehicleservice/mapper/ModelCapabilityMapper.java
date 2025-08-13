package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.Capability;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleModelFeaturesEntity} and {@link Capability} DTO.
 */
@Component
public class ModelCapabilityMapper {

  /**
   * Extracts the feature code from {@link TeleModelFeaturesEntity}.
   *
   * @param teleModelFeaturesEntity the TeleModelFeatures entity
   * @return The feature code
   */
  public String toFeatureCode(final TeleModelFeaturesEntity teleModelFeaturesEntity) {
    return teleModelFeaturesEntity.getFeature().getFeatureCode();
  }

  /**
   * Maps {@link TeleModelFeaturesEntity} to {@link Capability} DTO.
   *
   * @param teleModelFeaturesEntity the {@link TeleModelFeaturesEntity} entity
   * @return The {@link Capability}
   */
  public Capability toDto(final TeleModelFeaturesEntity teleModelFeaturesEntity) {
    Capability capability = new Capability();



    if (teleModelFeaturesEntity.getEffectiveStartDate() != null) {
      capability.setEffectiveStartDate(teleModelFeaturesEntity.getEffectiveStartDate().atStartOfDay().atOffset(ZoneOffset.UTC));
    }

    if (teleModelFeaturesEntity.getEffectiveEndDate() != null) {
      capability.setEffectiveEndDate(teleModelFeaturesEntity.getEffectiveEndDate().atStartOfDay().atOffset(ZoneOffset.UTC));
    }

    var feature = teleModelFeaturesEntity.getFeature();
    capability.setCapabilityCode(feature.getFeatureCode());
    capability.setRemoteFlag(feature.isRemoteFlg());
    capability.setDescription(feature.getFeatureDesc());
    capability.setDeliveryChecklistFlag(feature.getDeliveryChecklistFlg());
    capability.setMonroneyLabelFlag(feature.getMonroneyLabelFlg());

    return capability;
  }
}
