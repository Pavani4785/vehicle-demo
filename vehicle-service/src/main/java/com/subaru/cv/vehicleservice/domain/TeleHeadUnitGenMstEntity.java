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
 * The type Tele head unit gen mst entity.
 */
@Entity
@Table(name = "TELE_HEAD_UNIT_GEN_MST")
@SuppressWarnings("unused")
public class TeleHeadUnitGenMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HEAD_UNIT_GEN_ID", nullable = false)
  private long id;

  @Size(max = 15)
  @NotNull
  @Column(name = "HEAD_UNIT_GEN", nullable = false, length = 15)
  private String headUnitGen;

  @Size(max = 250)
  @Column(name = "HU_PROVIDER_CODE", length = 250)
  private String huProviderCode;

  @OneToMany(mappedBy = "headUnitGenMaster")
  private Set<TeleLocalModelMstEntity> teleLocalModelMsts = new LinkedHashSet<>();

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  /**
   * Gets head unit gen.
   *
   * @return the head unit gen
   */
  public @Size(max = 15) @NotNull String getHeadUnitGen() {
    return headUnitGen;
  }

  /**
   * Sets head unit gen.
   *
   * @param headUnitGen the head unit gen
   */
  public void setHeadUnitGen(@Size(max = 15) @NotNull String headUnitGen) {
    this.headUnitGen = headUnitGen;
  }

  /**
   * Gets hu provider code.
   *
   * @return the hu provider code
   */
  public @Size(max = 250) String getHuProviderCode() {
    return huProviderCode;
  }

  /**
   * Sets hu provider code.
   *
   * @param huProviderCode the hu provider code
   */
  public void setHuProviderCode(@Size(max = 250) String huProviderCode) {
    this.huProviderCode = huProviderCode;
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
}
