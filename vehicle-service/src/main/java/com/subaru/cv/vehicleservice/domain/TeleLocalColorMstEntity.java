package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * The type Tele local color mst entity.
 */
@Entity
@Table(name = "TELE_LOCAL_COLOR_MST")
@SuppressWarnings("unused")
public class TeleLocalColorMstEntity extends AuditableEntity {
  @Id
  @Column(name = "LOCAL_COLOR_ID", nullable = false)
  private long id;

  @Size(max = 250)
  @Column(name = "LOCAL_EXT_COLOR_DESC", length = 250)
  private String localExtColorDesc;

  @Size(max = 250)
  @NotNull
  @Column(name = "BASE_COLOR", nullable = false, length = 250)
  private String baseColor;

  @Size(max = 15)
  @NotNull
  @Column(name = "EXTERIOR_COLOR_CODE", nullable = false, length = 15)
  private String exteriorColorCode;

  @Size(max = 15)
  @NotNull
  @Column(name = "INTERIOR_COLOR_CODE", nullable = false, length = 15)
  private String interiorColorCode;

  @Size(max = 250)
  @Column(name = "LOCAL_INT_COLOR_DESC", length = 250)
  private String localIntColorDesc;

  @Size(max = 100)
  @NotNull
  @Column(name = "LOCAL_COLOR_KEY", nullable = false, length = 100)
  private String localColorKey;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "MARKET_ID", nullable = false)
  private TeleMarketEntity market;

  @OneToMany(mappedBy = "localColor")
  private Set<TeleVinMasterEntity> teleVinMasters = new LinkedHashSet<>();

  /**
   * Gets base color.
   *
   * @return the base color
   */
  public @Size(max = 250) @NotNull String getBaseColor() {
    return baseColor;
  }

  /**
   * Sets base color.
   *
   * @param baseColor the base color
   */
  public void setBaseColor(@Size(max = 250) @NotNull String baseColor) {
    this.baseColor = baseColor;
  }

  /**
   * Gets exterior color code.
   *
   * @return the exterior color code
   */
  public @Size(max = 15) @NotNull String getExteriorColorCode() {
    return exteriorColorCode;
  }

  /**
   * Sets exterior color code.
   *
   * @param exteriorColorCode the exterior color code
   */
  public void setExteriorColorCode(@Size(max = 15) @NotNull String exteriorColorCode) {
    this.exteriorColorCode = exteriorColorCode;
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
   * Gets interior color code.
   *
   * @return the interior color code
   */
  public @Size(max = 15) @NotNull String getInteriorColorCode() {
    return interiorColorCode;
  }

  /**
   * Sets interior color code.
   *
   * @param interiorColorCode the interior color code
   */
  public void setInteriorColorCode(@Size(max = 15) @NotNull String interiorColorCode) {
    this.interiorColorCode = interiorColorCode;
  }

  /**
   * Gets local color key.
   *
   * @return the local color key
   */
  public @Size(max = 100) @NotNull String getLocalColorKey() {
    return localColorKey;
  }

  /**
   * Sets local color key.
   *
   * @param localColorKey the local color key
   */
  public void setLocalColorKey(@Size(max = 100) @NotNull String localColorKey) {
    this.localColorKey = localColorKey;
  }

  /**
   * Gets local ext color desc.
   *
   * @return the local ext color desc
   */
  public @Size(max = 250) String getLocalExtColorDesc() {
    return localExtColorDesc;
  }

  /**
   * Sets local ext color desc.
   *
   * @param localExtColorDesc the local ext color desc
   */
  public void setLocalExtColorDesc(@Size(max = 250) String localExtColorDesc) {
    this.localExtColorDesc = localExtColorDesc;
  }

  /**
   * Gets local int color desc.
   *
   * @return the local int color desc
   */
  public @Size(max = 250) String getLocalIntColorDesc() {
    return localIntColorDesc;
  }

  /**
   * Sets local int color desc.
   *
   * @param localIntColorDesc the local int color desc
   */
  public void setLocalIntColorDesc(@Size(max = 250) String localIntColorDesc) {
    this.localIntColorDesc = localIntColorDesc;
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
