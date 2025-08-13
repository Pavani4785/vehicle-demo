package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleDcmMstEntity} and {@link Dcm} DTO.
 */
@Component
public class DcmMapper {

  /**
   * Maps {@link TeleDcmMstEntity} to {@link Dcm} DTO.
   *
   * @param teleDcmMstEntity the TeleDcm entity
   * @return the {@link Dcm}
   */
  public Dcm toDto(final TeleDcmMstEntity teleDcmMstEntity) {

    var dcm = new Dcm();
    dcm.setDcmNumber(teleDcmMstEntity.getDcmNumber());
    dcm.setImei(teleDcmMstEntity.getImei());
    dcm.setIccid(teleDcmMstEntity.getIccid());
    dcm.setMsisdn(teleDcmMstEntity.getMsisdn());
    dcm.setManufacturerCode(teleDcmMstEntity.getMfrCode());

    if (teleDcmMstEntity.getPairingDate() != null) {
      dcm.setPairingDate(teleDcmMstEntity.getPairingDate().atOffset(ZoneOffset.UTC));
    }

    if (teleDcmMstEntity.getShippingDate() != null) {
      dcm.setShippingDate(teleDcmMstEntity.getShippingDate().atOffset(ZoneOffset.UTC));
    }

    if (teleDcmMstEntity.getScrapDate() != null) {
      dcm.setScrapDate(teleDcmMstEntity.getScrapDate().atOffset(ZoneOffset.UTC));
    }

    return dcm;
  }
}
