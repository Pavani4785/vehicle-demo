package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.domain.TeleVinMasterEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link TeleVinMasterEntity} and {@link Vehicle} DTO.
 */
@Component
public class VehicleMapper {

  private final LocalModelMapper localModelMapper;
  private final LocalColorMapper localColorMapper;
  private final ModelCapabilityMapper modelCapabilityMapper;
  private final GlobalModelMapper globalModelMapper;
  private final MarketMapper marketMapper;

  /**
   * Instantiates a new Vehicle mapper.
   *
   * @param localModelMapper the {@link LocalModelMapper} mapper
   * @param localColorMapper the {@link LocalColorMapper} mapper
   * @param modelCapabilityMapper the {@link ModelCapabilityMapper} mapper
   * @param globalModelMapper the {@link GlobalModelMapper} mapper
   * @param marketMapper the {@link MarketMapper} mapper
   */
  public VehicleMapper(
      final LocalModelMapper localModelMapper,
      final LocalColorMapper localColorMapper,
      final ModelCapabilityMapper modelCapabilityMapper,
      final GlobalModelMapper globalModelMapper,
      final MarketMapper marketMapper
  ) {
    this.localModelMapper = localModelMapper;
    this.localColorMapper = localColorMapper;
    this.modelCapabilityMapper = modelCapabilityMapper;
    this.globalModelMapper = globalModelMapper;
    this.marketMapper = marketMapper;
  }

  /**
   * Maps {@link TeleVinMasterEntity} to {@link Vehicle} DTO.
   *
   * @param teleVinMasterEntity the {@link TeleVinMasterEntity} entity
   * @return the {@link Vehicle}
   */
  public Vehicle toDto(final TeleVinMasterEntity teleVinMasterEntity) {

    var vehicle = new Vehicle();
    vehicle.setVin(teleVinMasterEntity.getVin());
    vehicle.setTelematicsVehicleId(teleVinMasterEntity.getVehicleId());
    vehicle.setTelematicsFlag(teleVinMasterEntity.isTelematicsFlag());

    if (teleVinMasterEntity.getProductionDate() != null) {
      vehicle.setProductionDate(
          teleVinMasterEntity.getProductionDate()
      );
    }

    vehicle.setKeyNumber(teleVinMasterEntity.getKeyNumber());
    vehicle.setIspId(teleVinMasterEntity.getIspId());
    vehicle.setImmobilizerKey(teleVinMasterEntity.getImmobilizerKey());
    vehicle.setEngineNumber(teleVinMasterEntity.getEngineNumber());
    vehicle.setDistributorCode(teleVinMasterEntity.getDistributorCode());
    vehicle.setCaseNumber(teleVinMasterEntity.getCaseNumber());

    var carlineMst = teleVinMasterEntity.getLocalModel().getCarlineMaster();
    vehicle.setCarlineId(carlineMst.getId());
    vehicle.setCarlineName(carlineMst.getCarline());
    vehicle.setCarlineDescription(carlineMst.getCarlineDesc());

    vehicle.setVehicleSource(teleVinMasterEntity.getVehicleSource());

    vehicle.setCapabilities(
        teleVinMasterEntity
            .getLocalModel()
            .getTeleModelFeatures()
            .stream()
            .map(modelCapabilityMapper::toFeatureCode)
            .collect(Collectors.toUnmodifiableSet())
    );

    vehicle.setLocalColor(
        localColorMapper.toDto(
            teleVinMasterEntity.getLocalColor()
        )
    );

    vehicle.setModel(
        localModelMapper.toDto(
            teleVinMasterEntity.getLocalModel()
        )
    );

    vehicle.setMarketCode(
        marketMapper.toMarketCode(
            teleVinMasterEntity.getMarket()
        )
    );

    vehicle.setGlobalModel(
        globalModelMapper.toDto(
            teleVinMasterEntity.getGlobalModel(),
            teleVinMasterEntity.getLocalSpec()
        )
    );

    return vehicle;
  }
}
