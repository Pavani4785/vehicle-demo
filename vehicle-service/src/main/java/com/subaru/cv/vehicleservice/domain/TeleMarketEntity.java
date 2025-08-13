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
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * A TeleMarketEntity represents a market in the telematics system.
 * A market represents the combination of an organization (e.g. SOA) and a country (e.g. USA).
 */
@Entity
@Table(name = "TELE_MARKET")
@SuppressWarnings("unused")
public class TeleMarketEntity extends AuditableEntity {
  @Id
  @Column(name = "MARKET_ID", nullable = false)
  private long id;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "ORG_ID", nullable = false)
  private TeleOrgEntity org;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "COUNTRY_CODE", nullable = false)
  private TeleCountryEntity country;

  @OneToMany(mappedBy = "market")
  private Set<TeleLocalColorMstEntity> teleLocalColorMsts = new LinkedHashSet<>();

  @OneToMany(mappedBy = "market")
  private Set<TeleLocalModelSpecMstEntity> teleLocalModelSpecMsts = new LinkedHashSet<>();

  @OneToMany(mappedBy = "market")
  private Set<TeleVinMasterEntity> teleVinMasters = new LinkedHashSet<>();

  @OneToMany(mappedBy = "market")
  private Set<TeleDcmGenMstEntity> teleDcmGenMsts = new LinkedHashSet<>();

  @OneToMany(mappedBy = "market")
  private Set<TeleLocalModelMstEntity> teleLocalModelMsts = new LinkedHashSet<>();

  @OneToMany(mappedBy = "market")
  private Set<TeleHeadUnitGenMstEntity> teleHeadUnitGenMsts = new LinkedHashSet<>();

  /**
   * Gets country.
   *
   * @return the country
   */
  public @NotNull TeleCountryEntity getCountry() {
    return country;
  }

  /**
   * Sets country.
   *
   * @param country the country
   */
  public void setCountry(@NotNull TeleCountryEntity country) {
    this.country = country;
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
   * Gets org.
   *
   * @return the org
   */
  public @NotNull TeleOrgEntity getOrg() {
    return org;
  }

  /**
   * Sets org.
   *
   * @param org the org
   */
  public void setOrg(@NotNull TeleOrgEntity org) {
    this.org = org;
  }

  /**
   * Gets tele dcm gen msts.
   *
   * @return the tele dcm gen msts
   */
  public Set<TeleDcmGenMstEntity> getTeleDcmGenMsts() {
    return teleDcmGenMsts;
  }

  /**
   * Sets tele dcm gen msts.
   *
   * @param teleDcmGenMsts the tele dcm gen msts
   */
  public void setTeleDcmGenMsts(Set<TeleDcmGenMstEntity> teleDcmGenMsts) {
    this.teleDcmGenMsts = teleDcmGenMsts;
  }

  /**
   * Gets tele head unit gen msts.
   *
   * @return the tele head unit gen msts
   */
  public Set<TeleHeadUnitGenMstEntity> getTeleHeadUnitGenMsts() {
    return teleHeadUnitGenMsts;
  }

  /**
   * Sets tele head unit gen msts.
   *
   * @param teleHeadUnitGenMsts the tele head unit gen msts
   */
  public void setTeleHeadUnitGenMsts(Set<TeleHeadUnitGenMstEntity> teleHeadUnitGenMsts) {
    this.teleHeadUnitGenMsts = teleHeadUnitGenMsts;
  }

  /**
   * Gets tele local color msts.
   *
   * @return the tele local color msts
   */
  public Set<TeleLocalColorMstEntity> getTeleLocalColorMsts() {
    return teleLocalColorMsts;
  }

  /**
   * Sets tele local color msts.
   *
   * @param teleLocalColorMsts the tele local color msts
   */
  public void setTeleLocalColorMsts(Set<TeleLocalColorMstEntity> teleLocalColorMsts) {
    this.teleLocalColorMsts = teleLocalColorMsts;
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
   * Gets tele local model spec msts.
   *
   * @return the tele local model spec msts
   */
  public Set<TeleLocalModelSpecMstEntity> getTeleLocalModelSpecMsts() {
    return teleLocalModelSpecMsts;
  }

  /**
   * Sets tele local model spec msts.
   *
   * @param teleLocalModelSpecMsts the tele local model spec msts
   */
  public void setTeleLocalModelSpecMsts(Set<TeleLocalModelSpecMstEntity> teleLocalModelSpecMsts) {
    this.teleLocalModelSpecMsts = teleLocalModelSpecMsts;
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
