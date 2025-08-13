package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.model.LocalColor;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleLocalColorMstEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class LocalColorMapperTest {

  private final LocalColorMapper localColorMapper;

  @Autowired
  LocalColorMapperTest(final LocalColorMapper localColorMapper) {
    this.localColorMapper = localColorMapper;
  }

  /**
   * To dto test.
   */
  @Test
  void test_toDto_validTeleLocalColorMstEntity_mappedLocalColor() {
    // Arrange
    TeleLocalColorMstEntity localColorMstEntity = Instancio.of(TeleLocalColorMstEntity.class)
        .withMaxDepth(1)
        .create();

    // Act
    LocalColor color = localColorMapper.toDto(localColorMstEntity);

    // Assert
    assertEquals(color.getLocalColorKey(), localColorMstEntity.getLocalColorKey());
    assertEquals(color.getBaseColor(), localColorMstEntity.getBaseColor());
    assertEquals(color.getExteriorColorCode(), localColorMstEntity.getExteriorColorCode());
    assertEquals(color.getExteriorColorDescription(), localColorMstEntity.getLocalExtColorDesc());
    assertEquals(color.getInteriorColorCode(), localColorMstEntity.getInteriorColorCode());
    assertEquals(color.getInteriorColorDescription(), localColorMstEntity.getLocalIntColorDesc());
  }
}
