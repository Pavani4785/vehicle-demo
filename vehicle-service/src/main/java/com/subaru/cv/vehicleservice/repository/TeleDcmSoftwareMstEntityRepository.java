package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleDcmSoftwareMstEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeleDcmSoftwareMstEntityRepository extends JpaRepository<TeleDcmSoftwareMstEntity, Long> {

  @Override
  @Query("""
              SELECT dcmSoftware
              FROM TeleDcmSoftwareMstEntity dcmSoftware
              LEFT JOIN FETCH dcmSoftware.teleDcmMsts dcmMsts
              LEFT JOIN FETCH dcmMsts.teleVinStatuses
              WHERE dcmSoftware.id = :dcmSoftwareId
      """)
  Optional<TeleDcmSoftwareMstEntity> findById(final Long dcmSoftwareId);

  /**
   * Find a {@link TeleDcmSoftwareMstEntity} by the vin of a vehicle it is associated with.
   *
   * @param vin The VIN of the vehicle.
   * @return an Optional containing the {@link TeleDcmSoftwareMstEntity} if found
   */
  @Query("""
              SELECT dcmSoftware
              FROM TeleDcmSoftwareMstEntity dcmSoftware
              LEFT JOIN FETCH dcmSoftware.teleDcmMsts dcmMsts
              LEFT JOIN FETCH dcmMsts.teleVinStatuses vehicleStatuses
              LEFT JOIN vehicleStatuses.teleVinMaster vehicle
              WHERE vehicle.vin = :vin
      """)
  Optional<TeleDcmSoftwareMstEntity> findByVin(final String vin);
}
