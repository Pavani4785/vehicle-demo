package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleDcmMstEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeleDcmMstEntityRepository extends JpaRepository<TeleDcmMstEntity, Long> {
  /**
   * Find a {@link TeleDcmMstEntity} by the vin of a vehicle it is associated with.
   *
   * @param vin The VIN of the vehicle.
   * @return an Optional containing the {@link TeleDcmMstEntity} if found
   */
  @Query("""
      SELECT dcmMst
      FROM TeleDcmMstEntity dcmMst
      LEFT JOIN dcmMst.teleVinStatuses vehicleStatuses
      LEFT JOIN vehicleStatuses.teleVinMaster vehicles
      WHERE vehicles.vin = :vin
      """)
  Optional<TeleDcmMstEntity> findByVin(final String vin);
}
