package com.subaru.cv.vehicleservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.subaru.cv.model.Dcm;
import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleDcmSoftwareMstEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class DcmSoftwareMapperTest {

  private final DcmSoftwareMapper dcmSoftwareMapper;

  @Autowired
  DcmSoftwareMapperTest(final DcmSoftwareMapper dcmSoftwareMapper) {
    this.dcmSoftwareMapper = dcmSoftwareMapper;
  }

  /**
   * To dto test.
   */
  @Test
  void test_toDto_validDcmSoftwareEntity_mappedDcmSoftware() {
    // Arrange
    TeleDcmSoftwareMstEntity dcmSoftwareMstEntity = Instancio.of(TeleDcmSoftwareMstEntity.class)
        .withMaxDepth(4)
        .create();

    // Act
    DcmSoftware dcmSoftware = dcmSoftwareMapper.toDto(dcmSoftwareMstEntity);

    // Assert
    assertEquals(dcmSoftware.getContiRNumber(), dcmSoftwareMstEntity.getContiRNbr());
    assertEquals(dcmSoftware.getOfficialLoadsInfo(), dcmSoftwareMstEntity.getOfficialLoadsInfo());
    assertEquals(dcmSoftware.getReleaseNote(), dcmSoftwareMstEntity.getReleaseNote());
    assertEquals(dcmSoftware.getShortVersion(), dcmSoftwareMstEntity.getDcmSoftwareShortVersion());
    assertEquals(dcmSoftware.getVersion(), dcmSoftwareMstEntity.getDcmSoftwareVersion());

    // DcmMapper tested elsewhere, so we just check that the DCM numbers match
    var entityDcmNumbers = dcmSoftwareMstEntity.getTeleDcmMsts().stream().map(TeleDcmMstEntity::getDcmNumber).toList();
    var dtoDcmNumbers = dcmSoftware.getDcms().stream().map(Dcm::getDcmNumber).toList();
    assertTrue(dtoDcmNumbers.containsAll(entityDcmNumbers));
    assertTrue(entityDcmNumbers.containsAll(dtoDcmNumbers));

    // DcmGenerationMapper tested elsewhere, so we just check that the DCM generation matches
    assertEquals(dcmSoftware.getDcmGeneration().getDcmGeneration(), dcmSoftwareMstEntity.getDcmGenMaster().getDcmGen());
  }
}
