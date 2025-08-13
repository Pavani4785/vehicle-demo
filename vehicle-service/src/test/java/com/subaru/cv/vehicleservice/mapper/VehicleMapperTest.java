package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import com.subaru.cv.vehicleservice.domain.TeleVinMasterEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class VehicleMapperTest {

  private final VehicleMapper vehicleMapper;

  @Autowired
  VehicleMapperTest(final VehicleMapper vehicleMapper) {
    this.vehicleMapper = vehicleMapper;
  }

  /**
   * Test to dto.
   */
  @Test
  void test_toDto_validVehicleEntity_mappedVehicle() {
    // Arrange
    TeleVinMasterEntity vehicleEntity = Instancio.of(TeleVinMasterEntity.class)
        .withMaxDepth(5)
        .generate(Select.field(TeleLocalModelMstEntity.class, "modelYear"), gen ->
            gen.ints().range(2000, 2024))
        .create();

    // Act
    Vehicle result = vehicleMapper.toDto(vehicleEntity);

    // Assert
    assertEquals(vehicleEntity.getVin(), result.getVin());
    assertVehicleDataMapped(vehicleEntity, result);
    assertMarketMapped(vehicleEntity, result);
    assertGlobalModelMapped(vehicleEntity, result);
    assertLocalModelMapped(vehicleEntity, result);
    assertLocalColorMapped(vehicleEntity, result);
    assertCapabilitiesMapped(vehicleEntity, result);
  }

  private void assertCapabilitiesMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    Set<String> mappedCapabilities = new HashSet<>(result.getCapabilities());

    Set<String> vehicleEntityFeatureCodes = vehicleEntity
        .getLocalModel()
        .getTeleModelFeatures()
        .stream()
        .map(TeleModelFeaturesEntity::getFeature)
        .map(TeleFeaturesMstEntity::getFeatureCode)
        .collect(Collectors.toSet());

    assertEquals(vehicleEntityFeatureCodes, mappedCapabilities);
  }

  private void assertLocalColorMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    assertEquals(vehicleEntity.getLocalColor().getLocalColorKey(), result.getLocalColor().getLocalColorKey());
    assertEquals(vehicleEntity.getLocalColor().getBaseColor(), result.getLocalColor().getBaseColor());
    assertEquals(vehicleEntity.getLocalColor().getExteriorColorCode(), result.getLocalColor().getExteriorColorCode());
    assertEquals(vehicleEntity.getLocalColor().getLocalExtColorDesc(), result.getLocalColor().getExteriorColorDescription());
    assertEquals(vehicleEntity.getLocalColor().getInteriorColorCode(), result.getLocalColor().getInteriorColorCode());
    assertEquals(vehicleEntity.getLocalColor().getLocalIntColorDesc(), result.getLocalColor().getInteriorColorDescription());
  }

  private void assertLocalModelMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    assertEquals(vehicleEntity.getLocalModel().getHeadUnitGenMaster().getHeadUnitGen(), result.getModel().getHeadUnitGeneration());
    assertEquals(vehicleEntity.getLocalModel().getHeadUnitGenMaster().getHuProviderCode(), result.getModel().getHeadUnitProviderCode());
    assertEquals(vehicleEntity.getLocalModel().getModelYear(), result.getModel().getModelYear());
    assertEquals(vehicleEntity.getLocalModel().getOptionCode(), result.getModel().getOptionCode());
    assertEquals(vehicleEntity.getLocalModel().getDcmGenMaster().getDcmGen(), result.getModel().getDcmGeneration());
    assertEquals(vehicleEntity.getLocalModel().getModelDesc(), result.getModel().getModelDescription());
    assertEquals(vehicleEntity.getLocalModel().getBodyStyle(), result.getModel().getBodyStyle());
    assertEquals(vehicleEntity.getLocalModel().getTransmission(), result.getModel().getTransmission());
    assertEquals(vehicleEntity.getLocalModel().getTurbo(), result.getModel().getTurbo());
    assertEquals(vehicleEntity.getLocalModel().getDriveTrain(), result.getModel().getDriveTrain());
    assertEquals(vehicleEntity.getLocalModel().getEngineSize(), result.getModel().getEngineSize());
    assertEquals(vehicleEntity.getLocalModel().getTrim(), result.getModel().getModelTrim());
    assertEquals(vehicleEntity.getLocalModel().getModelTrimDesc(), result.getModel().getModelTrimDescription());
    assertEquals(vehicleEntity.getLocalModel().getLocalModelKey(), result.getModel().getModelKey());
    assertEquals(vehicleEntity.getLocalModel().getMotivePower(), result.getModel().getMotivePower());
  }

  private void assertGlobalModelMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    assertEquals(vehicleEntity.getGlobalModel().getGlobalModelCode(), result.getGlobalModel().getGlobalModelCode());
    assertEquals(vehicleEntity.getGlobalModel().getGlobalExteriorColor(), result.getGlobalModel().getGlobalExteriorColorCode());
    assertEquals(vehicleEntity.getGlobalModel().getGlobalOptionCode(), result.getGlobalModel().getGlobalOptionCode());
    assertEquals(vehicleEntity.getGlobalModel().getGlobalSpecCode(), result.getGlobalModel().getGlobalSpecificationCode());
    assertEquals(vehicleEntity.getGlobalModel().getGlobalInteriorColor(), result.getGlobalModel().getGlobalInteriorColorCode());
  }

  private void assertMarketMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    String marketCode = vehicleEntity.getMarket().getOrg().getOrgName() + "_" + vehicleEntity.getMarket().getCountry().getCountryCode();
    assertEquals(marketCode, result.getMarketCode());
  }

  private void assertVehicleDataMapped(TeleVinMasterEntity vehicleEntity, Vehicle result) {
    assertEquals(vehicleEntity.getCaseNumber(), result.getCaseNumber());
    assertEquals(vehicleEntity.getVehicleSource(), result.getVehicleSource());
    assertEquals(vehicleEntity.getCaseNumber(), result.getCaseNumber());
    assertEquals(vehicleEntity.getDistributorCode(), result.getDistributorCode());
    assertEquals(vehicleEntity.getEngineNumber(), result.getEngineNumber());
    assertEquals(vehicleEntity.getImmobilizerKey(), result.getImmobilizerKey());
    assertEquals(vehicleEntity.getKeyNumber(), result.getKeyNumber());
    assertEquals(vehicleEntity.getVehicleId(), result.getTelematicsVehicleId());
    assertEquals(vehicleEntity.isTelematicsFlag(), result.getTelematicsFlag());
    assertEquals(vehicleEntity.getLocalModel().getCarlineMaster().getId(), result.getCarlineId());
    assertEquals(vehicleEntity.getLocalModel().getCarlineMaster().getCarlineDesc(), result.getCarlineDescription());
    assertEquals(vehicleEntity.getLocalModel().getCarlineMaster().getCarline(), result.getCarlineName());
  }


}
