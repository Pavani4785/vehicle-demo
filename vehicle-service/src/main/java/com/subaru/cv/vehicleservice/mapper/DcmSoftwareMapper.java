package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.domain.TeleDcmSoftwareMstEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleDcmSoftwareMstEntity} and {@link DcmSoftware} DTO.
 */
@Component
public class DcmSoftwareMapper {

  private final DcmMapper dcmMapper;
  private final DcmGenerationMapper dcmGenerationMapper;

  /**
   * Instantiates a new Dcm software mapper.
   *
   * @param dcmMapper           the dcm mapper
   * @param dcmGenerationMapper the dcm generation mapper
   */
  public DcmSoftwareMapper(
      final DcmMapper dcmMapper,
      final DcmGenerationMapper dcmGenerationMapper
  ) {
    this.dcmMapper = dcmMapper;
    this.dcmGenerationMapper = dcmGenerationMapper;
  }

  /**
   * Maps a {@link TeleDcmSoftwareMstEntity} to a {@link DcmSoftware} DTO.
   *
   * @param teleDcmSoftwareMstEntity the {@link TeleDcmSoftwareMstEntity} entity
   * @return the {@link DcmSoftware} object
   */
  public DcmSoftware toDto(final TeleDcmSoftwareMstEntity teleDcmSoftwareMstEntity) {
    var dcmSoftware = new DcmSoftware();
    dcmSoftware.setContiRNumber(teleDcmSoftwareMstEntity.getContiRNbr());
    dcmSoftware.setOfficialLoadsInfo(teleDcmSoftwareMstEntity.getOfficialLoadsInfo());
    dcmSoftware.setReleaseNote(teleDcmSoftwareMstEntity.getReleaseNote());
    dcmSoftware.setShortVersion(teleDcmSoftwareMstEntity.getDcmSoftwareShortVersion());
    dcmSoftware.setVersion(teleDcmSoftwareMstEntity.getDcmSoftwareVersion());

    dcmSoftware.setDcms(
        teleDcmSoftwareMstEntity
            .getTeleDcmMsts()
            .stream()
            .map(dcmMapper::toDto)
            .collect(Collectors.toUnmodifiableSet())
    );

    dcmSoftware.setDcmGeneration(
        dcmGenerationMapper.toDto(
            teleDcmSoftwareMstEntity.getDcmGenMaster()
        )
    );

    return dcmSoftware;
  }
}
