package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.LocalColor;
import com.subaru.cv.vehicleservice.domain.TeleLocalColorMstEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleLocalColorMstEntity} and {@link LocalColor} DTO.
 */
@Component
public class LocalColorMapper {

  /**
   * Maps {@link TeleLocalColorMstEntity} to {@link LocalColor} DTO.
   *
   * @param teleLocalColorMstEntity the TeleLocalColor entity
   * @return the {@link LocalColor}
   */
  public LocalColor toDto(final TeleLocalColorMstEntity teleLocalColorMstEntity) {
    var localColor = new LocalColor();
    localColor.setLocalColorKey(teleLocalColorMstEntity.getLocalColorKey());
    localColor.setBaseColor(teleLocalColorMstEntity.getBaseColor());
    localColor.setExteriorColorCode(teleLocalColorMstEntity.getExteriorColorCode());
    localColor.setExteriorColorDescription(teleLocalColorMstEntity.getLocalExtColorDesc());
    localColor.setInteriorColorCode(teleLocalColorMstEntity.getInteriorColorCode());
    localColor.setInteriorColorDescription(teleLocalColorMstEntity.getLocalIntColorDesc());
    return localColor;
  }
}
