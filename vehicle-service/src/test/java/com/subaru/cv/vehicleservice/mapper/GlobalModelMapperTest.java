package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleGlobalModelMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelSpecMstEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class GlobalModelMapperTest {

  @Autowired
  private GlobalModelMapper globalModelMapper;

  @Test
  void test_toDto_validEntities_validDto() {
    var teleGlobalModelMstEntity = Instancio.create(TeleGlobalModelMstEntity.class);
    var teleLocalModelSpecMstEntity = Instancio.create(TeleLocalModelSpecMstEntity.class);

    var globalModel = globalModelMapper.toDto(teleGlobalModelMstEntity, teleLocalModelSpecMstEntity);

    // fields mapped from TeleGlobalModelMstEntity
    assertEquals(teleGlobalModelMstEntity.getGlobalModelCode(), globalModel.getGlobalModelCode());
    assertEquals(teleGlobalModelMstEntity.getGlobalOptionCode(), globalModel.getGlobalOptionCode());
    assertEquals(teleGlobalModelMstEntity.getGlobalSpecCode(), globalModel.getGlobalSpecificationCode());
    assertEquals(teleGlobalModelMstEntity.getGlobalExteriorColor(), globalModel.getGlobalExteriorColorCode());
    assertEquals(teleGlobalModelMstEntity.getGlobalInteriorColor(), globalModel.getGlobalInteriorColorCode());

    // fields mapped from TeleLocalModelSpecMstEntity
    assertEquals(teleLocalModelSpecMstEntity.getSpecCode(), globalModel.getSpecCode());
    assertEquals(teleLocalModelSpecMstEntity.getSpecCertificationVerbiage(), globalModel.getSpecCertificationVerbiage());
  }
}
