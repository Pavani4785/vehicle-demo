package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import com.subaru.cv.vehicleservice.domain.TeleVinMasterEntity;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Vehicle repository.
 */
@Repository
public interface TeleVinMasterEntityRepository extends JpaRepository<TeleVinMasterEntity, String> {
  /**
   * Find by vins.
   *
   * @param vins the vins.
   * @return the set of {@link TeleVinMasterEntity} entities.
   */
  @Query("""
      SELECT vehicle
      FROM TeleVinMasterEntity vehicle
      LEFT JOIN FETCH vehicle.localModel localModel
      LEFT JOIN FETCH localModel.teleModelFeatures
      WHERE vehicle.vin IN :vins
      """)
  Set<TeleVinMasterEntity> findByVinIn(final Collection<String> vins);

  /**
   * Find {@link TeleVinMasterEntity} by vin.
   * <br/>
   * This entity has a one-to-many relationship with {@link TeleModelFeaturesEntity}.
   * <br/>
   * Only the {@link TeleModelFeaturesEntity} that are considered "active" will be returned.
   *
   * @param vin The vin to search by
   * @return the {@link TeleVinMasterEntity} joined only with active features
   */
  @Query("""
            SELECT vinMaster
            FROM TeleVinMasterEntity vinMaster
            LEFT JOIN FETCH vinMaster.localModel localModel
            LEFT JOIN FETCH localModel.teleModelFeatures modelFeature
            WHERE vinMaster.vin = :vin
            AND ((modelFeature.effectiveStartDate <= CURRENT_DATE)
            AND (modelFeature.effectiveEndDate IS NULL OR modelFeature.effectiveEndDate >= CURRENT_DATE))
      """)
  Optional<TeleVinMasterEntity> findByVinFilterActiveFeatures(final String vin);
}
