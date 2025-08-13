package com.subaru.cv.vehicleservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("h2")
class TeleDcmGenMstEntityRepositoryTest {

  @Autowired
  private TeleDcmGenMstEntityRepository teleDcmGenMstEntityRepository;

  @Test
  void test_findByVin_validVin_correctEntity() {
    String vin = "4S4BRBCC4C1225127";
    long expectedDcmGenMstId = 5L;

    var teleDcmGenMstEntity = teleDcmGenMstEntityRepository.findByVin(vin);

    assertTrue(teleDcmGenMstEntity.isPresent());
    assertEquals(expectedDcmGenMstId, teleDcmGenMstEntity.get().getId());
  }
}
