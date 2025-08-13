package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.cafe.core.exception.CafeInternalServerErrorException;
import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.TeleLocalModelMstEntity;
import com.subaru.cv.vehicleservice.mapper.LocalModelMapper;
import com.subaru.cv.vehicleservice.repository.TeleLocalModelMstEntityRepository;
import jakarta.persistence.PersistenceException;
import java.util.Optional;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving model information.
 */
@Service
public class ModelService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);
  private final LocalModelMapper modelMapper;
  private final TeleLocalModelMstEntityRepository modelMstEntityRepository;

  /**
   * Instantiates a new {@link ModelService}.
   *
   * @param modelMstEntityRepository      Repository for {@link TeleLocalModelMstEntity} entities.
   * @param modelMapper                   The mapper
   */
  public ModelService(
      final TeleLocalModelMstEntityRepository modelMstEntityRepository,
      final LocalModelMapper modelMapper
  ) {
    this.modelMstEntityRepository = modelMstEntityRepository;
    this.modelMapper = modelMapper;
  }

  /**
   * Gets {@link Model} by model key and market.
   *
   * @param modelKey The model key
   * @param market   The {@link MarketType}
   * @return the {@link Model}
   * @throws CafeInternalServerErrorException if multiple results are found representing a data integrity issue
   */
  public Optional<Model> getModelByModelKey(final String modelKey, final MarketType market) {
    try {
      return modelMstEntityRepository
          .findByLocalModelKeyAndMarketId(modelKey, market.getMarketId())
          .map(modelMapper::toDto);

      // In QA environment, this method may throw a NonUniqueResultException representing a data integrity issue
    } catch (PersistenceException e) {
      if (e.getCause() instanceof NonUniqueResultException) {
        LOGGER.error("Non-unique result for modelKey: {} and marketId: {}", modelKey, market, e);
        throw new CafeInternalServerErrorException("Multiple results found for the given modelKey and marketId");
      } else {
        throw e;
      }
    }
  }

  /**
   * Gets model by vin.
   *
   * @param vin the vin
   * @return the model by vin
   */
  public Optional<Model> getModelByVin(final String vin) {
    return modelMstEntityRepository
        .findByTeleVinMastersVin(vin)
        .map(modelMapper::toDto);
  }
}
