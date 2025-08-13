package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.domain.TeleDcmSoftwareMstEntity;
import com.subaru.cv.vehicleservice.mapper.DcmSoftwareMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmSoftwareMstEntityRepository;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DcmSoftwareServiceTest {

  @InjectMocks
  private DcmSoftwareService dcmSoftwareService;

  @Mock
  private TeleDcmSoftwareMstEntityRepository teleDcmSoftwareMstEntityRepository;

  @Mock
  private DcmSoftwareMapper dcmSoftwareMapper;

  @Test
  void test_getDcmSoftwareByDcmSoftware_dcmSoftwareId_dcmSoftware() {
    // Arrange
    long dcmSoftwareId = Instancio.gen().longs().get();
    var dcmSoftwareMstEntity = Instancio.create(TeleDcmSoftwareMstEntity.class);
    var dcmSoftware = Instancio.create(DcmSoftware.class);

    when(teleDcmSoftwareMstEntityRepository.findById(dcmSoftwareId))
        .thenReturn(
            Optional.of(dcmSoftwareMstEntity)
        );

    when(dcmSoftwareMapper.toDto(dcmSoftwareMstEntity))
        .thenReturn(dcmSoftware);

    // Act
    var result = dcmSoftwareService.getDcmSoftwareByDcmSoftwareId(dcmSoftwareId);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcmSoftware, result.get());
  }

  @Test
  void test_getDcmSoftwareByVin_validVin_dcmSoftware() {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .get();

    var teleDcmSoftwareMstEntity = Instancio.create(TeleDcmSoftwareMstEntity.class);
    var dcmSoftware = Instancio.create(DcmSoftware.class);

    when(teleDcmSoftwareMstEntityRepository.findByVin(vin))
        .thenReturn(
            Optional.of(teleDcmSoftwareMstEntity)
        );

    when(dcmSoftwareMapper.toDto(teleDcmSoftwareMstEntity))
        .thenReturn(dcmSoftware);

    // Act
    var result = dcmSoftwareService.getDcmSoftwareByVin(vin);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcmSoftware, result.get());
  }
}
