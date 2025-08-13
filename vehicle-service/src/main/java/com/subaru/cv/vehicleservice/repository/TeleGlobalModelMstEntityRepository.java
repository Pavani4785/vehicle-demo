package com.subaru.cv.vehicleservice.repository;

import com.subaru.cv.vehicleservice.domain.TeleGlobalModelMstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Tele global model mst entity repository.
 */
@Repository
public interface TeleGlobalModelMstEntityRepository extends JpaRepository<TeleGlobalModelMstEntity, Long> {
}
