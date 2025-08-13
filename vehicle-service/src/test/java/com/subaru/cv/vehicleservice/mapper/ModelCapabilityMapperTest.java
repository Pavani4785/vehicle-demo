package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.Capability;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import java.time.LocalDate;
import java.util.Set;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class ModelCapabilityMapperTest {

  private final ModelCapabilityMapper modelCapabilityMapper;

  @Autowired
  ModelCapabilityMapperTest(final ModelCapabilityMapper modelCapabilityMapper) {
    this.modelCapabilityMapper = modelCapabilityMapper;
  }

  /**
   * To dto test.
   */
  @Test
  public void test_toDto_validTeleModelFeaturesEntity_mappedCapability() {
    // Arrange
    Set<TeleModelFeaturesEntity> features = Instancio.ofSet(TeleModelFeaturesEntity.class)
        .withMaxDepth(3)
        .withNullable(Select.all(LocalDate.class)) // Start and end date can be null
        .create();

    for (TeleModelFeaturesEntity featureEntity : features) {
      // Act
      Capability capability = modelCapabilityMapper.toDto(featureEntity);

      // Assert
      Assertions.assertEquals(capability.getCapabilityCode(), featureEntity.getFeature().getFeatureCode());
      Assertions.assertEquals(capability.getDescription(), featureEntity.getFeature().getFeatureDesc());
      if (featureEntity.getEffectiveStartDate() != null) {
        Assertions.assertEquals(capability.getEffectiveStartDate().toLocalDate(), featureEntity.getEffectiveStartDate());
      } else {
        Assertions.assertNull(capability.getEffectiveStartDate());
      }
      if (featureEntity.getEffectiveEndDate() != null) {
        Assertions.assertEquals(capability.getEffectiveEndDate().toLocalDate(), featureEntity.getEffectiveEndDate());
      } else {
        Assertions.assertNull(capability.getEffectiveEndDate());
      }
      Assertions.assertEquals(capability.getDeliveryChecklistFlag(), featureEntity.getFeature().getDeliveryChecklistFlg());
      Assertions.assertEquals(capability.getMonroneyLabelFlag(), featureEntity.getFeature().getMonroneyLabelFlg());
      Assertions.assertEquals(capability.getRemoteFlag(), featureEntity.getFeature().isRemoteFlg());
    }
  }

  /**
   * To feature code.
   */
  @Test
  public void test_toFeatureCode_validTeleModelFeaturesEntity_featureCode() {
    // Arrange
    TeleModelFeaturesEntity feature = new TeleModelFeaturesEntity();
    TeleFeaturesMstEntity teleFeaturesMstEntity = new TeleFeaturesMstEntity();
    teleFeaturesMstEntity.setFeatureCode("testCode");
    feature.setFeature(teleFeaturesMstEntity);

    // Act
    String featureCode = modelCapabilityMapper.toFeatureCode(feature);

    // Assert
    Assertions.assertEquals("testCode", featureCode);
  }
}
