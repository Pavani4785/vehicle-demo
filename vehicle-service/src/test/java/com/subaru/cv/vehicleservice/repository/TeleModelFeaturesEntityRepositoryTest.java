package com.subaru.cv.vehicleservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.subaru.cv.vehicleservice.domain.MarketType;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("h2")
class TeleModelFeaturesEntityRepositoryTest {

  private final TeleModelFeaturesEntityRepository repository;

  @Autowired
  TeleModelFeaturesEntityRepositoryTest(TeleModelFeaturesEntityRepository repository) {
    this.repository = repository;
  }

  @Test
  void test_findByLocalModelLocalModelKeyAndLocalModelMarketId_validModelKeyValidMarket_correctAmountOfEntities() {
    // Arrange
    String modelKey = "RRF-31-2024.0";
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var modelFeaturesEntities = repository.findByLocalModelKeyAndMarket(modelKey, marketId);

    // Assert
    assertEquals(71, modelFeaturesEntities.size());
  }

  @Test
  void test_findByLocalModelLocalModelKeyAndLocalModelMarketId_nonExistentModelKey_emptySet() {
    // Arrange
    String modelKey = "RRF-31-5000.0";
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var modelFeaturesEntities = repository.findByLocalModelKeyAndMarket(modelKey, marketId);

    // Assert
    assertTrue(modelFeaturesEntities.isEmpty());
  }

  @Test
  void test_findCapabilityCodesByLocalModelKeyAndMarketId_validModelKeyValidMarket_correctAmountOfCapabilityCodes() {
    // Arrange
    String modelKey = "RRF-31-2024.0";
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var capabilityCodes = repository.findCapabilityCodesByLocalModelKeyAndMarketId(modelKey, marketId);

    // Assert
    assertEquals(71, capabilityCodes.size());
  }

  @Test
  void test_findCapabilityCodesByLocalModelKeyAndMarketId_nonExistentModelKey_emptySet() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var capabilityCodes = repository.findCapabilityCodesByLocalModelKeyAndMarketId(modelKey, marketId);

    // Assert
    assertTrue(capabilityCodes.isEmpty());
  }

  @Test
  void test_findActiveCapabilityCodesByLocalModelKeyAndMarketId_validModelKeyValidMarket_filtersInactiveCapabilities() {
    // Arrange
    String modelKey = "RRF-31-2024.0";
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var capabilityCodes = repository.findActiveCapabilityCodesByLocalModelKeyAndMarketId(modelKey, marketId);

    // Assert
    assertEquals(70, capabilityCodes.size());
  }

  @Test
  void test_findActiveCapabilityCodesByLocalModelKeyAndMarketId_nonExistentModelKey_emptySet() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var marketId = MarketType.SOA_USA.getMarketId();

    // Act
    var capabilityCodes = repository.findActiveCapabilityCodesByLocalModelKeyAndMarketId(modelKey, marketId);

    // Assert
    assertTrue(capabilityCodes.isEmpty());
  }
}
