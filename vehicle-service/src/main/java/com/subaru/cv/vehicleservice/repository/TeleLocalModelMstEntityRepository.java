package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface TeleLocalModel mst entity repository.
 */
@Repository
public interface TeleLocalModelMstEntityRepository extends JpaRepository<TeleLocalModelMstEntity, String> {
  /**
   * Find by model code TeleLocalModel mst entity.
   *
   * @param localModelKey the local model key
   * @param marketId      the market id
   * @return the TeleLocalModel mst entity
   */
  Optional<TeleLocalModelMstEntity> findByLocalModelKeyAndMarketId(final String localModelKey, final long marketId);

  /**
   * Find a {@link TeleLocalModelMstEntity} by the vin of a vehicle it is associated with.
   *
   * @param vin The vin of the vehicle.
   * @return the {@link TeleLocalModelMstEntity} if found
   */
  Optional<TeleLocalModelMstEntity> findByTeleVinMastersVin(final String vin);
}
