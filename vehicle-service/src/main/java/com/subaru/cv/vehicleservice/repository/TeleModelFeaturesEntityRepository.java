package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Tele model features entity repository.
 */
@Repository
public interface TeleModelFeaturesEntityRepository extends JpaRepository<TeleModelFeaturesEntity, String> {

  /**
   * Finds a set of {@link TeleModelFeaturesEntity} by the given local model key and market.
   *
   * @param localModelKey the key of the local model
   * @param marketId      the market
   * @return a set of {@link TeleModelFeaturesEntity} matching the criteria
   */
  @Query("""
      SELECT modelFeature
      FROM TeleModelFeaturesEntity modelFeature
      WHERE modelFeature.localModel.localModelKey = :localModelKey
        AND modelFeature.localModel.market.id = :marketId
      """)
  Set<TeleModelFeaturesEntity> findByLocalModelKeyAndMarket(
      final String localModelKey,
      final long marketId
  );

  /**
   * Finds a set of feature codes by the given local model key and market ID.
   * The returned collection includes inactive features.
   *
   * @param localModelKey The key of the local model
   * @param marketId      The market ID
   * @return a set of feature codes
   * @see #findActiveCapabilityCodesByLocalModelKeyAndMarketId(String, long)
   */
  @Query("""
      SELECT modelFeature.feature.featureCode
      FROM TeleModelFeaturesEntity modelFeature
      WHERE modelFeature.localModel.localModelKey = :localModelKey
        AND modelFeature.localModel.market.id = :marketId
      """)
  Set<String> findCapabilityCodesByLocalModelKeyAndMarketId(
      final String localModelKey,
      final long marketId
  );

  /**
   * Finds active {@link TeleModelFeaturesEntity} by the given local model key and market ID.
   *
   * @param localModelKey the key of the local model
   * @param marketId      the ID of the market
   * @return a set of active {@link TeleModelFeaturesEntity}
   */
  @Query("""
            SELECT modelFeature.feature.featureCode
            FROM TeleModelFeaturesEntity modelFeature
            WHERE modelFeature.localModel.localModelKey = :localModelKey
              AND modelFeature.localModel.market.id = :marketId
              AND (modelFeature.effectiveStartDate <= CURRENT_DATE)
              AND (modelFeature.effectiveEndDate IS NULL OR modelFeature.effectiveEndDate >= CURRENT_DATE)
      """)
  Set<String> findActiveCapabilityCodesByLocalModelKeyAndMarketId(
      final String localModelKey,
      final long marketId
  );
}
