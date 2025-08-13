package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.TeleDcmGenMstEntity;
import com.subaru.cv.vehicleservice.mapper.DcmGenerationMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmGenMstEntityRepository;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DcmGenerationServiceTest {

  @InjectMocks
  private DcmGenerationService dcmGenerationService;

  @Mock
  private TeleDcmGenMstEntityRepository teleDcmGenMstEntityRepository;

  @Mock
  private DcmGenerationMapper dcmGenerationMapper;

  @Test
  void test_getDcmGenerationByDcmGeneration_validDcmGenerationAndMarketId_dcmGeneration() {
    // Arrange
    String dcmGenerationId = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var teleDcmGenMstEntity = Instancio.create(TeleDcmGenMstEntity.class);
    var dcmGenerationResponse = Instancio.create(DcmGeneration.class);

    when(teleDcmGenMstEntityRepository.findByDcmGenAndMarketId(dcmGenerationId, market.getMarketId()))
        .thenReturn(Optional.of(teleDcmGenMstEntity));

    when(dcmGenerationMapper.toDto(teleDcmGenMstEntity))
        .thenReturn(dcmGenerationResponse);

    // Act
    var result = dcmGenerationService.getDcmGenerationByDcmGeneration(dcmGenerationId, market);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcmGenerationResponse, result.get());
  }

  @Test
  void test_getDcmGenerationByVin_validVin_dcmGeneration() {
    // Arrange
    var dcmGenMstEntity = Instancio.create(TeleDcmGenMstEntity.class);
    var dcmGeneration = Instancio.create(DcmGeneration.class);
    var vin = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .get();

    when(teleDcmGenMstEntityRepository.findByVin(vin))
        .thenReturn(Optional.of(dcmGenMstEntity));

    when(dcmGenerationMapper.toDto(dcmGenMstEntity))
        .thenReturn(dcmGeneration);

    // Act
    var result = dcmGenerationService.getDcmGenerationByVin(vin);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcmGeneration, result.get());
  }
}
