package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.YesNoConverter;

/**
 * The type Tele local model mst entity.
 */
@Entity
@Table(name = "TELE_LOCAL_MODEL_MST")
@SuppressWarnings("unused")
public class TeleLocalModelMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LOCAL_MODEL_ID", nullable = false)
  private long id;

  @Size(max = 100)
  @NotNull
  @Column(name = "LOCAL_MODEL_KEY", nullable = false, length = 100)
  private String localModelKey;

  @Size(max = 6)
  @NotNull
  @Convert(converter = ModelYearConverter.class)
  @Column(name = "MODEL_YEAR", nullable = false, length = 6)
  private int modelYear;

  @Size(max = 15)
  @NotNull
  @Column(name = "MODEL_CODE", nullable = false, length = 15)
  private String modelCode;

  @Size(max = 15)
  @Column(name = "OPTION_CODE", length = 15)
  private String optionCode;

  @Size(max = 250)
  @Column(name = "MODEL_DESC", length = 250)
  private String modelDesc;

  @Size(max = 250)
  @Column(name = "BODY_STYLE", length = 250)
  private String bodyStyle;

  @Size(max = 250)
  @Column(name = "TRANSMISSION", length = 250)
  private String transmission;

  @Size(max = 250)
  @Column(name = "TURBO", length = 250)
  @Convert(converter = YesNoConverter.class)
  private Boolean turbo;

  @Size(max = 250)
  @Column(name = "DRIVE_TRAIN", length = 250)
  private String driveTrain;

  @Size(max = 250)
  @Column(name = "ENGINE_SIZE", length = 250)
  private String engineSize;

  @Size(max = 250)
  @Column(name = "TRIM", length = 250)
  private String trim;

  @Size(max = 250)
  @Column(name = "MODEL_TRIM_DESC", length = 250)
  private String modelTrimDesc;

  @Size(max = 15)
  @NotNull
  @Column(name = "MOTIVE_POWER", nullable = false, length = 15)
  private String motivePower;

  @Size(max = 4)
  @Column(name = "MODEL_YEAR_DESC", length = 4)
  private String modelYearDesc;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "DCM_GEN_ID")
  private TeleDcmGenMstEntity dcmGenMaster;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "HEAD_UNIT_GEN_ID")
  private TeleHeadUnitGenMstEntity headUnitGenMaster;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "CARLINE_ID", nullable = false)
  private TeleCarlineMstEntity carlineMaster;

  @OneToMany(mappedBy = "localModel")
  private Set<TeleVinMasterEntity> teleVinMasters = new LinkedHashSet<>();

  @OneToMany(mappedBy = "localModel")
  private Set<TeleModelFeaturesEntity> teleModelFeatures = new LinkedHashSet<>();

  /**
   * Gets body style.
   *
   * @return the body style
   */
  public @Size(max = 250) String getBodyStyle() {
    return bodyStyle;
  }

  /**
   * Sets body style.
   *
   * @param bodyStyle the body style
   */
  public void setBodyStyle(@Size(max = 250) String bodyStyle) {
    this.bodyStyle = bodyStyle;
  }

  /**
   * Gets carline master.
   *
   * @return the carline master
   */
  public @NotNull TeleCarlineMstEntity getCarlineMaster() {
    return carlineMaster;
  }

  /**
   * Sets carline master.
   *
   * @param carlineMaster the carline master
   */
  public void setCarlineMaster(@NotNull TeleCarlineMstEntity carlineMaster) {
    this.carlineMaster = carlineMaster;
  }

  /**
   * Gets dcm gen master.
   *
   * @return the dcm gen master
   */
  public TeleDcmGenMstEntity getDcmGenMaster() {
    return dcmGenMaster;
  }

  /**
   * Sets dcm gen master.
   *
   * @param dcmGenMaster the dcm gen master
   */
  public void setDcmGenMaster(TeleDcmGenMstEntity dcmGenMaster) {
    this.dcmGenMaster = dcmGenMaster;
  }

  /**
   * Gets drive train.
   *
   * @return the drive train
   */
  public @Size(max = 250) String getDriveTrain() {
    return driveTrain;
  }

  /**
   * Sets drive train.
   *
   * @param driveTrain the drive train
   */
  public void setDriveTrain(@Size(max = 250) String driveTrain) {
    this.driveTrain = driveTrain;
  }

  /**
   * Gets engine size.
   *
   * @return the engine size
   */
  public @Size(max = 250) String getEngineSize() {
    return engineSize;
  }

  /**
   * Sets engine size.
   *
   * @param engineSize the engine size
   */
  public void setEngineSize(@Size(max = 250) String engineSize) {
    this.engineSize = engineSize;
  }

  /**
   * Gets head unit gen master.
   *
   * @return the head unit gen master
   */
  public TeleHeadUnitGenMstEntity getHeadUnitGenMaster() {
    return headUnitGenMaster;
  }

  /**
   * Sets head unit gen master.
   *
   * @param headUnitGenMaster the head unit gen master
   */
  public void setHeadUnitGenMaster(TeleHeadUnitGenMstEntity headUnitGenMaster) {
    this.headUnitGenMaster = headUnitGenMaster;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets local model key.
   *
   * @return the local model key
   */
  public @Size(max = 100) @NotNull String getLocalModelKey() {
    return localModelKey;
  }

  /**
   * Sets local model key.
   *
   * @param localModelKey the local model key
   */
  public void setLocalModelKey(@Size(max = 100) @NotNull String localModelKey) {
    this.localModelKey = localModelKey;
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
   * Gets model code.
   *
   * @return the model code
   */
  public @Size(max = 15) @NotNull String getModelCode() {
    return modelCode;
  }

  /**
   * Sets model code.
   *
   * @param modelCode the model code
   */
  public void setModelCode(@Size(max = 15) @NotNull String modelCode) {
    this.modelCode = modelCode;
  }

  /**
   * Gets model desc.
   *
   * @return the model desc
   */
  public @Size(max = 250) String getModelDesc() {
    return modelDesc;
  }

  /**
   * Sets model desc.
   *
   * @param modelDesc the model desc
   */
  public void setModelDesc(@Size(max = 250) String modelDesc) {
    this.modelDesc = modelDesc;
  }

  /**
   * Gets model trim desc.
   *
   * @return the model trim desc
   */
  public @Size(max = 250) String getModelTrimDesc() {
    return modelTrimDesc;
  }

  /**
   * Sets model trim desc.
   *
   * @param modelTrimDesc the model trim desc
   */
  public void setModelTrimDesc(@Size(max = 250) String modelTrimDesc) {
    this.modelTrimDesc = modelTrimDesc;
  }

  /**
   * Gets model year.
   *
   * @return the model year
   */
  public @Size(max = 6) @NotNull int getModelYear() {
    return modelYear;
  }

  /**
   * Sets model year.
   *
   * @param modelYear the model year
   */
  public void setModelYear(@Size(max = 6) @NotNull int modelYear) {
    this.modelYear = modelYear;
  }

  /**
   * Gets model year desc.
   *
   * @return the model year desc
   */
  public @Size(max = 4) String getModelYearDesc() {
    return modelYearDesc;
  }

  /**
   * Sets model year desc.
   *
   * @param modelYearDesc the model year desc
   */
  public void setModelYearDesc(@Size(max = 4) String modelYearDesc) {
    this.modelYearDesc = modelYearDesc;
  }

  /**
   * Gets motive power.
   *
   * @return the motive power
   */
  public @Size(max = 15) @NotNull String getMotivePower() {
    return motivePower;
  }

  /**
   * Sets motive power.
   *
   * @param motivePower the motive power
   */
  public void setMotivePower(@Size(max = 15) @NotNull String motivePower) {
    this.motivePower = motivePower;
  }

  /**
   * Gets option code.
   *
   * @return the option code
   */
  public @Size(max = 15) String getOptionCode() {
    return optionCode;
  }

  /**
   * Sets option code.
   *
   * @param optionCode the option code
   */
  public void setOptionCode(@Size(max = 15) String optionCode) {
    this.optionCode = optionCode;
  }

  /**
   * Gets tele model features.
   *
   * @return the tele model features
   */
  public Set<TeleModelFeaturesEntity> getTeleModelFeatures() {
    return teleModelFeatures;
  }

  /**
   * Sets tele model features.
   *
   * @param teleModelFeatures the tele model features
   */
  public void setTeleModelFeatures(Set<TeleModelFeaturesEntity> teleModelFeatures) {
    this.teleModelFeatures = teleModelFeatures;
  }

  /**
   * Gets tele vin masters.
   *
   * @return the tele vin masters
   */
  public Set<TeleVinMasterEntity> getTeleVinMasters() {
    return teleVinMasters;
  }

  /**
   * Sets tele vin masters.
   *
   * @param teleVinMasters the tele vin masters
   */
  public void setTeleVinMasters(Set<TeleVinMasterEntity> teleVinMasters) {
    this.teleVinMasters = teleVinMasters;
  }

  /**
   * Gets transmission.
   *
   * @return the transmission
   */
  public @Size(max = 250) String getTransmission() {
    return transmission;
  }

  /**
   * Sets transmission.
   *
   * @param transmission the transmission
   */
  public void setTransmission(@Size(max = 250) String transmission) {
    this.transmission = transmission;
  }

  /**
   * Gets trim.
   *
   * @return the trim
   */
  public @Size(max = 250) String getTrim() {
    return trim;
  }

  /**
   * Sets trim.
   *
   * @param trim the trim
   */
  public void setTrim(@Size(max = 250) String trim) {
    this.trim = trim;
  }

  /**
   * Gets turbo.
   *
   * @return the turbo
   */
  public @Size(max = 250) Boolean getTurbo() {
    return turbo;
  }

  /**
   * Sets turbo.
   *
   * @param turbo the turbo
   */
  public void setTurbo(@Size(max = 250) Boolean turbo) {
    this.turbo = turbo;
  }
}
