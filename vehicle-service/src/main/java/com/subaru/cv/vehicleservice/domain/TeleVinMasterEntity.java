package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.YesNoConverter;

/**
 * The type Tele vin master entity.
 */
@Entity
@Table(name = "TELE_VIN_MASTER")
@SuppressWarnings("unused")
public class TeleVinMasterEntity extends AuditableEntity {
  @Id
  @Size(max = 17)
  @Column(name = "VIN", nullable = false, length = 17)
  private String vin;

  @Size(max = 20)
  @Column(name = "ENGINE_NUMBER", length = 20)
  private String engineNumber;

  @Size(max = 15)
  @Column(name = "KEY_NUMBER", length = 15)
  private String keyNumber;

  @Size(max = 15)
  @Column(name = "CASE_NUMBER", length = 15)
  private String caseNumber;

  @Column(name = "PRODUCTION_DATE")
  private LocalDate productionDate;

  @Size(max = 15)
  @Column(name = "VEHICLE_SOURCE", length = 15)
  private String vehicleSource;

  @Size(max = 15)
  @Column(name = "IMMOBILIZER_KEY", length = 15)
  private String immobilizerKey;

  @Size(max = 1)
  @NotNull
  @Column(name = "TELEMATICS_FLAG", nullable = false, length = 1)
  @Convert(converter = YesNoConverter.class)
  private boolean telematicsFlag;

  @Size(max = 255)
  @JdbcTypeCode(Types.VARCHAR)
  @Column(name = "VEHICLE_ID")
  private UUID vehicleId;

  @Size(max = 10)
  @Column(name = "ISP_ID", length = 10)
  private String ispId;

  @Size(max = 100)
  @Column(name = "DISTRIBUTOR_CODE", length = 100)
  private String distributorCode;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "GLOBAL_MODEL_ID")
  private TeleGlobalModelMstEntity globalModel;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "LOCAL_COLOR_ID")
  private TeleLocalColorMstEntity localColor;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "LOCAL_SPEC_ID")
  private TeleLocalModelSpecMstEntity localSpec;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "LOCAL_MODEL_ID")
  private TeleLocalModelMstEntity localModel;

  /**
   * Gets case number.
   *
   * @return the case number
   */
  public @Size(max = 15) String getCaseNumber() {
    return caseNumber;
  }

  /**
   * Sets case number.
   *
   * @param caseNumber the case number
   */
  public void setCaseNumber(@Size(max = 15) String caseNumber) {
    this.caseNumber = caseNumber;
  }

  /**
   * Gets distributor code.
   *
   * @return the distributor code
   */
  public @Size(max = 100) String getDistributorCode() {
    return distributorCode;
  }

  /**
   * Sets distributor code.
   *
   * @param distributorCode the distributor code
   */
  public void setDistributorCode(@Size(max = 100) String distributorCode) {
    this.distributorCode = distributorCode;
  }

  /**
   * Gets engine number.
   *
   * @return the engine number
   */
  public @Size(max = 20) String getEngineNumber() {
    return engineNumber;
  }

  /**
   * Sets engine number.
   *
   * @param engineNumber the engine number
   */
  public void setEngineNumber(@Size(max = 20) String engineNumber) {
    this.engineNumber = engineNumber;
  }

  /**
   * Gets global model.
   *
   * @return the global model
   */
  public TeleGlobalModelMstEntity getGlobalModel() {
    return globalModel;
  }

  /**
   * Sets global model.
   *
   * @param globalModel the global model
   */
  public void setGlobalModel(TeleGlobalModelMstEntity globalModel) {
    this.globalModel = globalModel;
  }

  /**
   * Gets immobilizer key.
   *
   * @return the immobilizer key
   */
  public @Size(max = 15) String getImmobilizerKey() {
    return immobilizerKey;
  }

  /**
   * Sets immobilizer key.
   *
   * @param immobilizerKey the immobilizer key
   */
  public void setImmobilizerKey(@Size(max = 15) String immobilizerKey) {
    this.immobilizerKey = immobilizerKey;
  }

  /**
   * Gets isp id.
   *
   * @return the isp id
   */
  public @Size(max = 10) String getIspId() {
    return ispId;
  }

  /**
   * Sets isp id.
   *
   * @param ispId the isp id
   */
  public void setIspId(@Size(max = 10) String ispId) {
    this.ispId = ispId;
  }

  /**
   * Gets key number.
   *
   * @return the key number
   */
  public @Size(max = 15) String getKeyNumber() {
    return keyNumber;
  }

  /**
   * Sets key number.
   *
   * @param keyNumber the key number
   */
  public void setKeyNumber(@Size(max = 15) String keyNumber) {
    this.keyNumber = keyNumber;
  }

  /**
   * Gets local color.
   *
   * @return the local color
   */
  public TeleLocalColorMstEntity getLocalColor() {
    return localColor;
  }

  /**
   * Sets local color.
   *
   * @param localColor the local color
   */
  public void setLocalColor(TeleLocalColorMstEntity localColor) {
    this.localColor = localColor;
  }

  /**
   * Gets local model.
   *
   * @return the local model
   */
  public TeleLocalModelMstEntity getLocalModel() {
    return localModel;
  }

  /**
   * Sets local model.
   *
   * @param localModel the local model
   */
  public void setLocalModel(TeleLocalModelMstEntity localModel) {
    this.localModel = localModel;
  }

  /**
   * Gets local spec.
   *
   * @return the local spec
   */
  public TeleLocalModelSpecMstEntity getLocalSpec() {
    return localSpec;
  }

  /**
   * Sets local spec.
   *
   * @param localSpec the local spec
   */
  public void setLocalSpec(TeleLocalModelSpecMstEntity localSpec) {
    this.localSpec = localSpec;
  }

  /**
   * Gets market.
   *
   * @return the market
   */
  public @NotNull TeleMarketEntity getMarket() {
    return market;
  }

  /**
   * Sets market.
   *
   * @param market the market
   */
  public void setMarket(@NotNull TeleMarketEntity market) {
    this.market = market;
  }

  /**
   * Gets production date.
   *
   * @return the production date
   */
  public LocalDate getProductionDate() {
    return productionDate;
  }

  /**
   * Sets production date.
   *
   * @param productionDate the production date
   */
  public void setProductionDate(LocalDate productionDate) {
    this.productionDate = productionDate;
  }

  /**
   * Is telematics flag boolean.
   *
   * @return the boolean
   */
  public @Size(max = 1) @NotNull boolean isTelematicsFlag() {
    return telematicsFlag;
  }

  /**
   * Sets telematics flag.
   *
   * @param telematicsFlag the telematics flag
   */
  public void setTelematicsFlag(@Size(max = 1) @NotNull boolean telematicsFlag) {
    this.telematicsFlag = telematicsFlag;
  }

  /**
   * Gets vehicle id.
   *
   * @return the vehicle id
   */
  public @Size(max = 255) UUID getVehicleId() {
    return vehicleId;
  }

  /**
   * Sets vehicle id.
   *
   * @param vehicleId the vehicle id
   */
  public void setVehicleId(@Size(max = 255) UUID vehicleId) {
    this.vehicleId = vehicleId;
  }

  /**
   * Gets vehicle source.
   *
   * @return the vehicle source
   */
  public @Size(max = 15) String getVehicleSource() {
    return vehicleSource;
  }

  /**
   * Sets vehicle source.
   *
   * @param vehicleSource the vehicle source
   */
  public void setVehicleSource(@Size(max = 15) String vehicleSource) {
    this.vehicleSource = vehicleSource;
  }

  /**
   * Gets vin.
   *
   * @return the vin
   */
  public @Size(max = 17) String getVin() {
    return vin;
  }

  /**
   * Sets vin.
   *
   * @param vin the vin
   */
  public void setVin(@Size(max = 17) String vin) {
    this.vin = vin;
  }
}
