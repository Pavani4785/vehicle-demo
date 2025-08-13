package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Tele org entity.
 */
@Entity
@Table(name = "TELE_ORG")
@SuppressWarnings("unused")
public class TeleOrgEntity extends AuditableEntity {
  @Id
  @Column(name = "ORG_ID", nullable = false)
  private long id;

  @Size(max = 100)
  @Enumerated(EnumType.STRING)
  @Column(name = "ORG_NAME", length = 100)
  private OrganizationType orgName;

  @OneToMany(mappedBy = "org")
  private Set<TeleMarketEntity> teleMarkets = new LinkedHashSet<>();

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
   * Gets org name.
   *
   * @return the org name
   */
  public @Size(max = 100) OrganizationType getOrgName() {
    return orgName;
  }

  /**
   * Sets org name.
   *
   * @param orgName the org name
   */
  public void setOrgName(@Size(max = 100) OrganizationType orgName) {
    this.orgName = orgName;
  }

  /**
   * Gets tele markets.
   *
   * @return the tele markets
   */
  public Set<TeleMarketEntity> getTeleMarkets() {
    return teleMarkets;
  }

  /**
   * Sets tele markets.
   *
   * @param teleMarkets the tele markets
   */
  public void setTeleMarkets(Set<TeleMarketEntity> teleMarkets) {
    this.teleMarkets = teleMarkets;
  }
}
