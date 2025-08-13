package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.BaseCapability;
import com.subaru.cv.model.Capability;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import com.subaru.cv.vehicleservice.mapper.CapabilityMapper;
import com.subaru.cv.vehicleservice.mapper.ModelCapabilityMapper;
import com.subaru.cv.vehicleservice.repository.TeleFeaturesMstEntityRepository;
import com.subaru.cv.vehicleservice.repository.TeleModelFeaturesEntityRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CapabilityServiceTest {

  @InjectMocks
  private CapabilityService capabilityService;
  @Mock
  private TeleFeaturesMstEntityRepository teleFeaturesMstEntityRepository;
  @Mock
  private TeleModelFeaturesEntityRepository teleModelFeaturesEntityRepository;
  @Mock
  private CapabilityMapper capabilityMapper;
  @Mock
  private ModelCapabilityMapper modelCapabilityMapper;

  @Test
  void test_getBaseCapability_validCapabilityCode_baseCapability() {
    // Arrange
    String capabilityCode = Instancio.gen().string().get();
    var baseCapability = Instancio.create(BaseCapability.class);
    var teleFeaturesMstEntity = Instancio.create(TeleFeaturesMstEntity.class);

    when(capabilityMapper
        .toDto(teleFeaturesMstEntity))
        .thenReturn(baseCapability);

    when(teleFeaturesMstEntityRepository
        .findByFeatureCode(capabilityCode))
        .thenReturn(Optional.of(teleFeaturesMstEntity));

    // Act
    var result = capabilityService.getBaseCapability(capabilityCode);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(baseCapability, result.get());
  }

  @Test
  void test_getBaseCapability_nonExistentCapabilityCode_emptyOptional() {
    // Arrange
    String capabilityCode = Instancio.create(String.class);

    when(teleFeaturesMstEntityRepository
        .findByFeatureCode(capabilityCode))
        .thenReturn(Optional.empty());

    // Act
    var result = capabilityService.getBaseCapability(capabilityCode);

    // Assert
    assertTrue(result.isEmpty());
  }

  @Test
  void test_getBaseCapabilities_existingCapabilities_listOfBaseCapabilities() {
    // Arrange
    var teleFeaturesMstEntityList = Instancio.createList(TeleFeaturesMstEntity.class);

    when(capabilityMapper.toDto(any(TeleFeaturesMstEntity.class)))
        .thenAnswer(invocation ->
            Instancio.create(BaseCapability.class)
        );

    when(teleFeaturesMstEntityRepository.findAll())
        .thenReturn(teleFeaturesMstEntityList);

    // Act
    var result = capabilityService.getBaseCapabilities();

    // Assert
    assertEquals(teleFeaturesMstEntityList.size(), result.size());
  }

  @Test
  void test_getBaseCapabilities_noCapabilities_emptyList() {
    // Arrange
    when(teleFeaturesMstEntityRepository.findAll())
        .thenReturn(List.of());

    // Act
    var result = capabilityService.getBaseCapabilities();

    // Assert
    assertTrue(result.isEmpty());
  }

  @Test
  void test_getCapabilities_validModelKeyAndValidMarketId_listOfCapabilities() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var teleModelFeaturesEntities = Instancio.createSet(TeleModelFeaturesEntity.class);

    when(modelCapabilityMapper.toDto(any(TeleModelFeaturesEntity.class)))
        .thenAnswer(invocation ->
            Instancio.create(Capability.class)
        );

    when(teleModelFeaturesEntityRepository
        .findByLocalModelKeyAndMarket(modelKey, market.getMarketId()))
        .thenReturn(teleModelFeaturesEntities);

    // Act
    var result = capabilityService.getCapabilities(modelKey, market);

    // Assert
    assertEquals(teleModelFeaturesEntities.size(), result.size());
  }

  @Test
  void test_getCapabilities_nonExistentModelKey_emptyList() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;

    when(teleModelFeaturesEntityRepository
        .findByLocalModelKeyAndMarket(modelKey, market.getMarketId()))
        .thenReturn(Set.of());

    // Act
    var result = capabilityService.getCapabilities(modelKey, market);

    // Assert
    assertTrue(result.isEmpty());
  }

  @Test
  void test_getCapabilityCodes_includeInactive_listOfCapabilityCodes() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var teleModelFeaturesEntities = Instancio.createSet(TeleModelFeaturesEntity.class);

    when(teleModelFeaturesEntityRepository.findCapabilityCodesByLocalModelKeyAndMarketId(modelKey, market.getMarketId()))
        .thenReturn(
            teleModelFeaturesEntities
                .stream()
                .map(TeleModelFeaturesEntity::getFeature)
                .map(TeleFeaturesMstEntity::getFeatureCode)
                .collect(Collectors.toUnmodifiableSet())
        );

    // Act
    var result = capabilityService.getCapabilityCodes(modelKey, market, true);

    // Assert
    assertEquals(teleModelFeaturesEntities.size(), result.size());
  }
}
