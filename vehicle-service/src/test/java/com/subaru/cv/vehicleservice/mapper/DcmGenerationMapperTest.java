package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleDcmGenMstEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class DcmGenerationMapperTest {

  private final DcmGenerationMapper dcmGenerationMapper;

  @Autowired
  DcmGenerationMapperTest(final DcmGenerationMapper dcmGenerationMapper) {
    this.dcmGenerationMapper = dcmGenerationMapper;
  }

  @Test
  void test_toDto_validTeleDcmGenMstEntity_mappedDcmGeneration() {
    // Arrange
    TeleDcmGenMstEntity dcmGenMstEntity = Instancio.of(TeleDcmGenMstEntity.class)
        .withMaxDepth(3)
        .create();

    // Act
    DcmGeneration dcmGeneration = dcmGenerationMapper.toDto(dcmGenMstEntity);

    // Assert
    assertEquals(dcmGeneration.getDcmGeneration(), dcmGenMstEntity.getDcmGen());
    assertEquals(dcmGeneration.getTspCode(), dcmGenMstEntity.getTspCode());
    assertEquals(dcmGeneration.getMarketCode(), dcmGenMstEntity.getMarket().getOrg().getOrgName() + "_" + dcmGenMstEntity.getMarket().getCountry().getCountryCode());
  }
}
