package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The type Tele dcm mst entity.
 */
@Entity
@Table(name = "TELE_DCM_MST")
@SuppressWarnings("unused")
public class TeleDcmMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DCM_ID", nullable = false)
  private long id;

  @Size(max = 10)
  @Column(name = "DCM_NUMBER", length = 10)
  private String dcmNumber;

  @Size(max = 16)
  @Column(name = "IMEI", length = 16)
  private String imei;

  @Size(max = 20)
  @Column(name = "ICCID", length = 20)
  private String iccid;

  @Size(max = 15)
  @Column(name = "MSISDN", length = 15)
  private String msisdn;

  @Column(name = "PAIRING_DATE")
  private LocalDateTime pairingDate;

  @Column(name = "SHIPPING_DATE")
  private LocalDateTime shippingDate;

  @Column(name = "SCRAP_DATE")
  private LocalDateTime scrapDate;

  @Size(max = 15)
  @Column(name = "MFR_CODE", length = 15)
  private String mfrCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "DCM_SOFTWARE_ID")
  private TeleDcmSoftwareMstEntity dcmSoftwareMaster;

  @OneToMany(mappedBy = "teleDcmMst")
  private Set<TeleVinStatusEntity> teleVinStatuses = new LinkedHashSet<>();

  /**
   * Gets dcm number.
   *
   * @return the dcm number
   */
  public @Size(max = 10) String getDcmNumber() {
    return dcmNumber;
  }

  /**
   * Sets dcm number.
   *
   * @param dcmNumber the dcm number
   */
  public void setDcmNumber(@Size(max = 10) String dcmNumber) {
    this.dcmNumber = dcmNumber;
  }

  /**
   * Gets dcm software master.
   *
   * @return the dcm software master
   */
  public TeleDcmSoftwareMstEntity getDcmSoftwareMaster() {
    return dcmSoftwareMaster;
  }

  /**
   * Sets dcm software master.
   *
   * @param dcmSoftwareMaster the dcm software master
   */
  public void setDcmSoftwareMaster(TeleDcmSoftwareMstEntity dcmSoftwareMaster) {
    this.dcmSoftwareMaster = dcmSoftwareMaster;
  }

  /**
   * Gets iccid.
   *
   * @return the iccid
   */
  public @Size(max = 20) String getIccid() {
    return iccid;
  }

  /**
   * Sets iccid.
   *
   * @param iccid the iccid
   */
  public void setIccid(@Size(max = 20) String iccid) {
    this.iccid = iccid;
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
   * Gets imei.
   *
   * @return the imei
   */
  public @Size(max = 16) String getImei() {
    return imei;
  }

  /**
   * Sets imei.
   *
   * @param imei the imei
   */
  public void setImei(@Size(max = 16) String imei) {
    this.imei = imei;
  }

  /**
   * Gets mfr code.
   *
   * @return the mfr code
   */
  public @Size(max = 15) String getMfrCode() {
    return mfrCode;
  }

  /**
   * Sets mfr code.
   *
   * @param mfrCode the mfr code
   */
  public void setMfrCode(@Size(max = 15) String mfrCode) {
    this.mfrCode = mfrCode;
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
   * Gets pairing date.
   *
   * @return the pairing date
   */
  public LocalDateTime getPairingDate() {
    return pairingDate;
  }

  /**
   * Sets pairing date.
   *
   * @param pairingDate the pairing date
   */
  public void setPairingDate(LocalDateTime pairingDate) {
    this.pairingDate = pairingDate;
  }

  /**
   * Gets scrap date.
   *
   * @return the scrap date
   */
  public LocalDateTime getScrapDate() {
    return scrapDate;
  }

  /**
   * Sets scrap date.
   *
   * @param scrapDate the scrap date
   */
  public void setScrapDate(LocalDateTime scrapDate) {
    this.scrapDate = scrapDate;
  }

  /**
   * Gets shipping date.
   *
   * @return the shipping date
   */
  public LocalDateTime getShippingDate() {
    return shippingDate;
  }

  /**
   * Sets shipping date.
   *
   * @param shippingDate the shipping date
   */
  public void setShippingDate(LocalDateTime shippingDate) {
    this.shippingDate = shippingDate;
  }

  /**
   * Gets tele vin statuses.
   *
   * @return the tele vin statuses
   */
  public Set<TeleVinStatusEntity> getTeleVinStatuses() {
    return teleVinStatuses;
  }

  /**
   * Sets tele vin statuses.
   *
   * @param teleVinStatuses the tele vin statuses
   */
  public void setTeleVinStatuses(Set<TeleVinStatusEntity> teleVinStatuses) {
    this.teleVinStatuses = teleVinStatuses;
  }
}
