package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleDcmGenMstEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The interface TeleDcmGenMst entity repository.
 */
@Repository
public interface TeleDcmGenMstEntityRepository extends JpaRepository<TeleDcmGenMstEntity, Long> {
  /**
   * Find by dcm gen and market tele dcm gen mst entity.
   *
   * @param dcmGeneration the dcm generation
   * @param marketId      the market
   * @return the teleDcmGen mst entity
   */
  Optional<TeleDcmGenMstEntity> findByDcmGenAndMarketId(final String dcmGeneration, final long marketId);

  /**
   * find {@link TeleDcmGenMstEntity} by vin of a vehicle it is associated with.
   *
   * @param vin The vin of the vehicle.
   * @return an Optional containing the {@link TeleDcmGenMstEntity} if found
   */
  @Query("""
      SELECT dcmGenMst
      FROM TeleDcmGenMstEntity dcmGenMst
      LEFT JOIN dcmGenMst.teleLocalModelMsts localModel
      LEFT JOIN localModel.teleVinMasters vehicle
      WHERE vehicle.vin = :vin
      """)
  Optional<TeleDcmGenMstEntity> findByVin(final String vin);
}
