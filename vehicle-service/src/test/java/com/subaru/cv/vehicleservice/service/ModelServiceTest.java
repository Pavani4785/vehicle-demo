package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import com.subaru.cv.vehicleservice.mapper.LocalModelMapper;
import com.subaru.cv.vehicleservice.repository.TeleLocalModelMstEntityRepository;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModelServiceTest {

  @InjectMocks
  private ModelService modelService;

  @Mock
  private TeleLocalModelMstEntityRepository modelMstEntityRepository;

  @Mock
  private LocalModelMapper modelMapper;

  @Test
  void test_getModelByModelKey_validModelKeyAndMarketId_model() {
    // Arrange
    String modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var teleLocalModelMstEntity = Instancio.create(TeleLocalModelMstEntity.class);
    var model = Instancio.create(Model.class);

    when(modelMstEntityRepository.findByLocalModelKeyAndMarketId(modelKey, market.getMarketId()))
        .thenReturn(Optional.of(teleLocalModelMstEntity));

    when(modelMapper.toDto(teleLocalModelMstEntity))
        .thenReturn(model);

    // Act
    var result = modelService.getModelByModelKey(modelKey, market);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(model, result.get());
  }

  @Test
  void test_getModelByVin_validVin_model() {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .get();

    var teleLocalModelMstEntity = Instancio.create(TeleLocalModelMstEntity.class);
    var model = Instancio.create(Model.class);

    when(modelMstEntityRepository.findByTeleVinMastersVin(vin))
        .thenReturn(Optional.of(teleLocalModelMstEntity));

    when(modelMapper.toDto(teleLocalModelMstEntity))
        .thenReturn(model);

    // Act
    var result = modelService.getModelByVin(vin);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(model, result.get());
  }
}
