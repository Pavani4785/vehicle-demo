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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The type Tele local model spec mst entity.
 */
@Entity
@Table(name = "TELE_LOCAL_MODEL_SPEC_MST")
@SuppressWarnings("unused")
public class TeleLocalModelSpecMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LOCAL_SPEC_ID", nullable = false)
  private long id;

  @Size(max = 15)
  @Column(name = "SPEC_CODE", length = 15)
  private String specCode;

  @Size(max = 250)
  @Column(name = "SPEC_CERTIFICATION_VERBIAGE", length = 250)
  private String specCertificationVerbiage;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  @OneToMany(mappedBy = "localSpec")
  private Set<TeleVinMasterEntity> teleVinMasters = new LinkedHashSet<>();

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
   * Gets spec certification verbiage.
   *
   * @return the spec certification verbiage
   */
  public @Size(max = 250) String getSpecCertificationVerbiage() {
    return specCertificationVerbiage;
  }

  /**
   * Sets spec certification verbiage.
   *
   * @param specCertificationVerbiage the spec certification verbiage
   */
  public void setSpecCertificationVerbiage(@Size(max = 250) String specCertificationVerbiage) {
    this.specCertificationVerbiage = specCertificationVerbiage;
  }

  /**
   * Gets spec code.
   *
   * @return the spec code
   */
  public @Size(max = 15) String getSpecCode() {
    return specCode;
  }

  /**
   * Sets spec code.
   *
   * @param specCode the spec code
   */
  public void setSpecCode(@Size(max = 15) String specCode) {
    this.specCode = specCode;
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
}
