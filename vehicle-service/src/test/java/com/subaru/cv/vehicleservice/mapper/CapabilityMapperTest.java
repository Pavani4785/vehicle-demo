package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import java.util.Set;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class CapabilityMapperTest {

  private final CapabilityMapper capabilityMapper;

  @Autowired
  CapabilityMapperTest(final CapabilityMapper capabilityMapper) {
    this.capabilityMapper = capabilityMapper;
  }

  @Test
  void test_toDto_validTeleFeaturesEntity_mappedBaseCapability() {
    // Arrange
    Set<TeleModelFeaturesEntity> features = Instancio.ofSet(TeleModelFeaturesEntity.class)
        .withMaxDepth(3)
        .create();

    // Act
    for (TeleModelFeaturesEntity feature : features) {
      TeleFeaturesMstEntity featuresMstEntity = feature.getFeature();

      com.subaru.cv.model.BaseCapability baseCapability = capabilityMapper.toDto(featuresMstEntity);

      // Assert
      assertEquals(baseCapability.getCapabilityCode(), featuresMstEntity.getFeatureCode());
      assertEquals(baseCapability.getDescription(), featuresMstEntity.getFeatureDesc());
      assertEquals(baseCapability.getRemoteFlag(), featuresMstEntity.isRemoteFlg());
      assertEquals(baseCapability.getDeliveryChecklistFlag(), featuresMstEntity.getDeliveryChecklistFlg());
      assertEquals(baseCapability.getMonroneyLabelFlag(), featuresMstEntity.getMonroneyLabelFlg());
    }
  }

  @Test
  void test_toEntity_validBaseCapability_mappedTeleFeaturesMstEntity() {
    // Arrange
    com.subaru.cv.model.BaseCapability baseCapability = Instancio.of(com.subaru.cv.model.BaseCapability.class)
        .withMaxDepth(1)
        .create();

    // Act
    TeleFeaturesMstEntity entity = capabilityMapper.toEntity(baseCapability);

    // Assert
    assertEquals(entity.getFeatureCode(), baseCapability.getCapabilityCode());
    assertEquals(entity.getFeatureDesc(), baseCapability.getDescription());
    assertEquals(entity.getDeliveryChecklistFlg(), baseCapability.getDeliveryChecklistFlag());
    assertEquals(entity.isRemoteFlg(), baseCapability.getRemoteFlag());
    assertEquals(entity.getMonroneyLabelFlg(), baseCapability.getMonroneyLabelFlag());
  }
}
