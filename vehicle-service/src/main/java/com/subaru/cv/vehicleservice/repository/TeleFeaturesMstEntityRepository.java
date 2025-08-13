package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The type Tele features mst entity repository.
 */
@Repository
public interface TeleFeaturesMstEntityRepository extends JpaRepository<TeleFeaturesMstEntity, String> {

  /**
   * Find by feature code.
   *
   * @param capabilityCode the capability code
   * @return the tele features mst entity
   */
  Optional<TeleFeaturesMstEntity> findByFeatureCode(final String capabilityCode);
}
