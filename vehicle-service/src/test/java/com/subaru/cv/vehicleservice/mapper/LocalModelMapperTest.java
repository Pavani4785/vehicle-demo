package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class LocalModelMapperTest {

  private final LocalModelMapper localModelMapper;
  private final MarketMapper marketMapper;

  @Autowired
  LocalModelMapperTest(
      final LocalModelMapper localModelMapper,
      final MarketMapper marketMapper
  ) {
    this.localModelMapper = localModelMapper;
    this.marketMapper = marketMapper;
  }

  /**
   * To dto test.
   */
  @Test
  void toDto() {
    // Arrange
    TeleLocalModelMstEntity localModelMstEntity = Instancio.of(TeleLocalModelMstEntity.class)
        .generate(Select.field("modelYear"), gen ->
            gen.ints().range(2000, 2024))
        .create();

    // Act
    Model model = localModelMapper.toDto(localModelMstEntity);

    // Assert
    assertEquals(localModelMstEntity.getTurbo(), model.getTurbo());
    assertEquals(localModelMstEntity.getBodyStyle(), model.getBodyStyle());
    assertEquals(localModelMstEntity.getModelDesc(), model.getModelDescription());
    assertEquals(localModelMstEntity.getLocalModelKey(), model.getModelKey());
    assertEquals(localModelMstEntity.getModelYear(), model.getModelYear());
    assertEquals(localModelMstEntity.getTrim(), model.getModelTrim());
    assertEquals(localModelMstEntity.getDcmGenMaster().getDcmGen(), model.getDcmGeneration());
    assertEquals(localModelMstEntity.getDriveTrain(), model.getDriveTrain());
    assertEquals(localModelMstEntity.getEngineSize(), model.getEngineSize());
    assertEquals(localModelMstEntity.getHeadUnitGenMaster().getHeadUnitGen(), model.getHeadUnitGeneration());
    assertEquals(marketMapper.toMarketCode(localModelMstEntity.getMarket()), model.getMarketCode());
  }
}
