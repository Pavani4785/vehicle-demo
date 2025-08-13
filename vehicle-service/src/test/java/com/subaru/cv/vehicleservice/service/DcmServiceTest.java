package com.subaru.cv.vehicleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import com.subaru.cv.vehicleservice.mapper.DcmMapper;
import com.subaru.cv.vehicleservice.repository.TeleDcmMstEntityRepository;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DcmServiceTest {

  @InjectMocks
  private DcmService dcmService;

  @Mock
  private TeleDcmMstEntityRepository teleDcmMstEntityRepository;

  @Mock
  private DcmMapper dcmMapper;

  @Test
  void test_getDcmByDcmId_validDcmId_dcm() {
    // Arrange
    long dcmId = Instancio.gen().longs().get();
    var dcm = Instancio.create(Dcm.class);
    var teleDcmMstEntity = Instancio.create(TeleDcmMstEntity.class);

    when(teleDcmMstEntityRepository.findById(dcmId))
        .thenReturn(Optional.of(teleDcmMstEntity));

    when(dcmMapper.toDto(teleDcmMstEntity))
        .thenReturn(dcm);

    // Act
    var result = dcmService.getDcmByDcmId(dcmId);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcm, result.get());
  }

  @Test
  void test_getDcmByVin_validVin_dcm() {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(17)
        .alphaNumeric()
        .get();

    var dcm = Instancio.create(Dcm.class);
    var teleDcmMstEntity = Instancio.create(TeleDcmMstEntity.class);

    when(teleDcmMstEntityRepository.findByVin(vin))
        .thenReturn(
            Optional.of(teleDcmMstEntity)
        );

    when(dcmMapper.toDto(teleDcmMstEntity))
        .thenReturn(dcm);

    // Act
    var result = dcmService.getDcmByVin(vin);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(dcm, result.get());
  }
}
