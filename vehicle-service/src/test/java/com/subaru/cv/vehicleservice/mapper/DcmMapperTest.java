package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class DcmMapperTest {

  private final DcmMapper dcmMapper;

  @Autowired
  DcmMapperTest(final DcmMapper dcmMapper) {
    this.dcmMapper = dcmMapper;
  }

  /**
   * Test to dto.
   */
  @Test
  void test_toDto_validTeleDcmMstEntity_mappedDcm() {
    // Arrange
    TeleDcmMstEntity entity = Instancio.of(TeleDcmMstEntity.class)
        .withMaxDepth(1)
        .create();

    // Act
    Dcm dcm = dcmMapper.toDto(entity);

    // Assert
    assertEquals(entity.getDcmNumber(), dcm.getDcmNumber());
    assertEquals(entity.getImei(), dcm.getImei());
    assertEquals(entity.getIccid(), dcm.getIccid());
    assertEquals(entity.getMsisdn(), dcm.getMsisdn());
    assertEquals(entity.getMfrCode(), dcm.getManufacturerCode());
    assertEquals(OffsetDateTime.of(entity.getPairingDate(), ZoneOffset.UTC), dcm.getPairingDate());
    assertEquals(OffsetDateTime.of(entity.getShippingDate(), ZoneOffset.UTC), dcm.getShippingDate());
    assertEquals(OffsetDateTime.of(entity.getScrapDate(), ZoneOffset.UTC), dcm.getScrapDate());
  }
}
