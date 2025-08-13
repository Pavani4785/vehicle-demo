package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The type Tele vin status entity.
 */
@Entity
@Table(name = "TELE_VIN_STATUS")
@SuppressWarnings("unused")
public class TeleVinStatusEntity extends AuditableEntity {
  @Id
  @Size(max = 17)
  @Column(name = "VIN", nullable = false, length = 17)
  private String vin;

  @Column(name = "COMM_CHECK_DATE")
  private LocalDateTime commCheckDate;

  @Column(name = "PROVISIONING_DATE")
  private LocalDateTime provisioningDate;

  @Size(max = 100)
  @Column(name = "PROVISIONING_STATUS", length = 100)
  private String provisioningStatus;

  @Size(max = 100)
  @Column(name = "MNO_STATE", length = 100)
  private String mnoState;

  @Column(name = "SUBSCRIPTION_FLAG")
  private Boolean subscriptionFlag;

  @Size(max = 100)
  @Column(name = "FOTA_PROCESS_NAME", length = 100)
  private String fotaProcessName;

  @Column(name = "FOTA_PROCESS_DATE")
  private LocalDateTime fotaProcessDate;

  @Size(max = 100)
  @Column(name = "FOTA_STATUS", length = 100)
  private String fotaStatus;

  @Column(name = "FOTA_VEHICLE_ID")
  private BigInteger fotaVehicleId;

  @Column(name = "FACTORY_FEED_DATE")
  private LocalDateTime factoryFeedDate;

  @Size(max = 15)
  @Column(name = "MSISDN", length = 15)
  private String msisdn;

  @Size(max = 200)
  @Column(name = "MODE_FILE_NAME", length = 200)
  private String modeFileName;

  @Size(max = 100)
  @Column(name = "SUBSCRIPTION_CATEGORY", length = 100)
  private String subscriptionCategory;

  @Column(name = "TRAFFIC_SUBSCRIPTION_FLAG")
  private Boolean trafficSubscriptionFlag;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "VIN", nullable = false)
  private TeleVinMasterEntity teleVinMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "DCM_ID")
  private TeleDcmMstEntity teleDcmMst;

  /**
   * Gets comm check date.
   *
   * @return the comm check date
   */
  public LocalDateTime getCommCheckDate() {
    return commCheckDate;
  }

  /**
   * Sets comm check date.
   *
   * @param commCheckDate the comm check date
   */
  public void setCommCheckDate(LocalDateTime commCheckDate) {
    this.commCheckDate = commCheckDate;
  }

  /**
   * Gets factory feed date.
   *
   * @return the factory feed date
   */
  public LocalDateTime getFactoryFeedDate() {
    return factoryFeedDate;
  }

  /**
   * Sets factory feed date.
   *
   * @param factoryFeedDate the factory feed date
   */
  public void setFactoryFeedDate(LocalDateTime factoryFeedDate) {
    this.factoryFeedDate = factoryFeedDate;
  }

  /**
   * Gets fota process date.
   *
   * @return the fota process date
   */
  public LocalDateTime getFotaProcessDate() {
    return fotaProcessDate;
  }

  /**
   * Sets fota process date.
   *
   * @param fotaProcessDate the fota process date
   */
  public void setFotaProcessDate(LocalDateTime fotaProcessDate) {
    this.fotaProcessDate = fotaProcessDate;
  }

  /**
   * Gets fota process name.
   *
   * @return the fota process name
   */
  public @Size(max = 100) String getFotaProcessName() {
    return fotaProcessName;
  }

  /**
   * Sets fota process name.
   *
   * @param fotaProcessName the fota process name
   */
  public void setFotaProcessName(@Size(max = 100) String fotaProcessName) {
    this.fotaProcessName = fotaProcessName;
  }

  /**
   * Gets fota status.
   *
   * @return the fota status
   */
  public @Size(max = 100) String getFotaStatus() {
    return fotaStatus;
  }

  /**
   * Sets fota status.
   *
   * @param fotaStatus the fota status
   */
  public void setFotaStatus(@Size(max = 100) String fotaStatus) {
    this.fotaStatus = fotaStatus;
  }

  /**
   * Gets fota vehicle id.
   *
   * @return the fota vehicle id
   */
  public BigInteger getFotaVehicleId() {
    return fotaVehicleId;
  }

  /**
   * Sets fota vehicle id.
   *
   * @param fotaVehicleId the fota vehicle id
   */
  public void setFotaVehicleId(BigInteger fotaVehicleId) {
    this.fotaVehicleId = fotaVehicleId;
  }

  /**
   * Gets mno state.
   *
   * @return the mno state
   */
  public @Size(max = 100) String getMnoState() {
    return mnoState;
  }

  /**
   * Sets mno state.
   *
   * @param mnoState the mno state
   */
  public void setMnoState(@Size(max = 100) String mnoState) {
    this.mnoState = mnoState;
  }

  /**
   * Gets mode file name.
   *
   * @return the mode file name
   */
  public @Size(max = 200) String getModeFileName() {
    return modeFileName;
  }

  /**
   * Sets mode file name.
   *
   * @param modeFileName the mode file name
   */
  public void setModeFileName(@Size(max = 200) String modeFileName) {
    this.modeFileName = modeFileName;
  }

  /**
   * Gets msisdn.
   *
   * @return the msisdn
   */
  public @Size(max = 15) String getMsisdn() {
    return msisdn;
  }

  /**
   * Sets msisdn.
   *
   * @param msisdn the msisdn
   */
  public void setMsisdn(@Size(max = 15) String msisdn) {
    this.msisdn = msisdn;
  }

  /**
   * Gets provisioning date.
   *
   * @return the provisioning date
   */
  public LocalDateTime getProvisioningDate() {
    return provisioningDate;
  }

  /**
   * Sets provisioning date.
   *
   * @param provisioningDate the provisioning date
   */
  public void setProvisioningDate(LocalDateTime provisioningDate) {
    this.provisioningDate = provisioningDate;
  }

  /**
   * Gets provisioning status.
   *
   * @return the provisioning status
   */
  public @Size(max = 100) String getProvisioningStatus() {
    return provisioningStatus;
  }

  /**
   * Sets provisioning status.
   *
   * @param provisioningStatus the provisioning status
   */
  public void setProvisioningStatus(@Size(max = 100) String provisioningStatus) {
    this.provisioningStatus = provisioningStatus;
  }

  /**
   * Gets subscription category.
   *
   * @return the subscription category
   */
  public @Size(max = 100) String getSubscriptionCategory() {
    return subscriptionCategory;
  }

  /**
   * Sets subscription category.
   *
   * @param subscriptionCategory the subscription category
   */
  public void setSubscriptionCategory(@Size(max = 100) String subscriptionCategory) {
    this.subscriptionCategory = subscriptionCategory;
  }

  /**
   * Gets subscription flag.
   *
   * @return the subscription flag
   */
  public Boolean getSubscriptionFlag() {
    return subscriptionFlag;
  }

  /**
   * Sets subscription flag.
   *
   * @param subscriptionFlag the subscription flag
   */
  public void setSubscriptionFlag(Boolean subscriptionFlag) {
    this.subscriptionFlag = subscriptionFlag;
  }

  /**
   * Gets tele dcm mst.
   *
   * @return the tele dcm mst
   */
  public TeleDcmMstEntity getTeleDcmMst() {
    return teleDcmMst;
  }

  /**
   * Sets tele dcm mst.
   *
   * @param teleDcmMst the tele dcm mst
   */
  public void setTeleDcmMst(TeleDcmMstEntity teleDcmMst) {
    this.teleDcmMst = teleDcmMst;
  }

  /**
   * Gets tele vin master.
   *
   * @return the tele vin master
   */
  public TeleVinMasterEntity getTeleVinMaster() {
    return teleVinMaster;
  }

  /**
   * Sets tele vin master.
   *
   * @param teleVinMaster the tele vin master
   */
  public void setTeleVinMaster(TeleVinMasterEntity teleVinMaster) {
    this.teleVinMaster = teleVinMaster;
  }

  /**
   * Gets traffic subscription flag.
   *
   * @return the traffic subscription flag
   */
  public Boolean getTrafficSubscriptionFlag() {
    return trafficSubscriptionFlag;
  }

  /**
   * Sets traffic subscription flag.
   *
   * @param trafficSubscriptionFlag the traffic subscription flag
   */
  public void setTrafficSubscriptionFlag(Boolean trafficSubscriptionFlag) {
    this.trafficSubscriptionFlag = trafficSubscriptionFlag;
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
