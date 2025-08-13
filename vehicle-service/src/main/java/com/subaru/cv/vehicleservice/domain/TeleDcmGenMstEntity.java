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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Tele dcm gen mst entity.
 */
@Entity
@Table(name = "TELE_DCM_GEN_MST")
@SuppressWarnings("unused")
public class TeleDcmGenMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DCM_GEN_ID", nullable = false)
  private long id;

  @Size(max = 15)
  @NotNull
  @Column(name = "DCM_GEN", nullable = false, length = 15)
  private String dcmGen;

  @Size(max = 15)
  @Column(name = "TSP_CODE", length = 15)
  private String tspCode;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  @OneToMany(mappedBy = "dcmGenMaster")
  private Set<TeleDcmSoftwareMstEntity> teleDcmSoftwareMsts = new LinkedHashSet<>();

  @OneToMany(mappedBy = "dcmGenMaster")
  private Set<TeleLocalModelMstEntity> teleLocalModelMsts = new LinkedHashSet<>();

  /**
   * Gets dcm gen.
   *
   * @return the dcm gen
   */
  public @Size(max = 15) @NotNull String getDcmGen() {
    return dcmGen;
  }

  /**
   * Sets dcm gen.
   *
   * @param dcmGen the dcm gen
   */
  public void setDcmGen(@Size(max = 15) @NotNull String dcmGen) {
    this.dcmGen = dcmGen;
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
   * Gets tele dcm software msts.
   *
   * @return the tele dcm software msts
   */
  public Set<TeleDcmSoftwareMstEntity> getTeleDcmSoftwareMsts() {
    return teleDcmSoftwareMsts;
  }

  /**
   * Sets tele dcm software msts.
   *
   * @param teleDcmSoftwareMsts the tele dcm software msts
   */
  public void setTeleDcmSoftwareMsts(Set<TeleDcmSoftwareMstEntity> teleDcmSoftwareMsts) {
    this.teleDcmSoftwareMsts = teleDcmSoftwareMsts;
  }

  /**
   * Gets tele local model msts.
   *
   * @return the tele local model msts
   */
  public Set<TeleLocalModelMstEntity> getTeleLocalModelMsts() {
    return teleLocalModelMsts;
  }

  /**
   * Sets tele local model msts.
   *
   * @param teleLocalModelMsts the tele local model msts
   */
  public void setTeleLocalModelMsts(Set<TeleLocalModelMstEntity> teleLocalModelMsts) {
    this.teleLocalModelMsts = teleLocalModelMsts;
  }

  /**
   * Gets tsp code.
   *
   * @return the tsp code
   */
  public @Size(max = 15) String getTspCode() {
    return tspCode;
  }

  /**
   * Sets tsp code.
   *
   * @param tspCode the tsp code
   */
  public void setTspCode(@Size(max = 15) String tspCode) {
    this.tspCode = tspCode;
  }
}
