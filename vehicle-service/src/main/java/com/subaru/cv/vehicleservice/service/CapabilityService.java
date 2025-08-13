package com.subaru.cv.vehicleservice.service;

import com.subaru.cv.model.BaseCapability;
import com.subaru.cv.model.Capability;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.TeleFeaturesMstEntity;
import com.subaru.cv.vehicleservice.domain.TeleModelFeaturesEntity;
import com.subaru.cv.vehicleservice.mapper.CapabilityMapper;
import com.subaru.cv.vehicleservice.mapper.ModelCapabilityMapper;
import com.subaru.cv.vehicleservice.repository.TeleFeaturesMstEntityRepository;
import com.subaru.cv.vehicleservice.repository.TeleModelFeaturesEntityRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving capability information.
 */
@Service
public class CapabilityService {
  private final TeleModelFeaturesEntityRepository teleModelFeaturesEntityRepository;
  private final TeleFeaturesMstEntityRepository teleFeaturesMstEntityRepository;
  private final CapabilityMapper capabilityMapper;
  private final ModelCapabilityMapper modelCapabilityMapper;

  /**
   * Instantiates a new Capability service.
   *
   * @param teleModelFeaturesEntityRepository Repository for {@link TeleModelFeaturesEntity} entities
   * @param teleFeaturesMstEntityRepository   Repository for {@link TeleFeaturesMstEntity} entities
   * @param capabilityMapper                  The capability mapper
   * @param modelCapabilityMapper             The modelCapability mapper
   */
  public CapabilityService(
      final TeleModelFeaturesEntityRepository teleModelFeaturesEntityRepository,
      final TeleFeaturesMstEntityRepository teleFeaturesMstEntityRepository,
      final CapabilityMapper capabilityMapper,
      final ModelCapabilityMapper modelCapabilityMapper
  ) {
    this.teleModelFeaturesEntityRepository = teleModelFeaturesEntityRepository;
    this.teleFeaturesMstEntityRepository = teleFeaturesMstEntityRepository;
    this.capabilityMapper = capabilityMapper;
    this.modelCapabilityMapper = modelCapabilityMapper;
  }

  /**
   * Gets {@link BaseCapability} corresponding to given capabilityCode.
   *
   * @param capabilityCode The capability code®
   * @return the {@link BaseCapability}
   */
  public Optional<BaseCapability> getBaseCapability(final String capabilityCode) {
    return teleFeaturesMstEntityRepository
        .findByFeatureCode(capabilityCode)
        .map(capabilityMapper::toDto);
  }

  /**
   * Gets all {@link BaseCapability}.
   *
   * @return the {@link BaseCapability BaseCapability} set
   */
  public Set<BaseCapability> getBaseCapabilities() {
    return teleFeaturesMstEntityRepository
        .findAll()
        .stream()
        .map(capabilityMapper::toDto)
        .collect(Collectors.toUnmodifiableSet());
  }

  /**
   * Gets {@link Capability} set of a given model.
   *
   * @param modelKey The model key
   * @param market   The market
   * @return the {@link Capability} set
   */
  public Set<Capability> getCapabilities(final String modelKey, final MarketType market) {
    return teleModelFeaturesEntityRepository
        .findByLocalModelKeyAndMarket(modelKey, market.getMarketId())
        .stream()
        .map(modelCapabilityMapper::toDto)
        .collect(Collectors.toUnmodifiableSet());
  }

  /**
   * Gets capability codes of a given model.
   *
   * @param modelKey        The model key
   * @param market          The market id
   * @param includeInactive Whether to include inactive features
   * @return the capability codes
   */
  public Set<String> getCapabilityCodes(
      final String modelKey,
      final MarketType market,
      final boolean includeInactive
  ) {
    return includeInactive
        ? teleModelFeaturesEntityRepository.findCapabilityCodesByLocalModelKeyAndMarketId(modelKey, market.getMarketId())
        : teleModelFeaturesEntityRepository.findActiveCapabilityCodesByLocalModelKeyAndMarketId(modelKey, market.getMarketId());
  }
}
