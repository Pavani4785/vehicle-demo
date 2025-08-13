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
 * The type Tele country entity.
 */
@Entity
@Table(name = "TELE_COUNTRY")
@SuppressWarnings("unused")
public class TeleCountryEntity extends AuditableEntity {
  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "COUNTRY_CODE", nullable = false, length = 3)
  private CountryCodeType countryCode;

  @Size(max = 100)
  @Column(name = "COUNTRY_NAME", length = 100)
  private String countryName;

  @OneToMany(mappedBy = "country")
  private Set<TeleMarketEntity> teleMarkets = new LinkedHashSet<>();

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

  /**
   * Gets country name.
   *
   * @return the country name
   */
  public @Size(max = 100) String getCountryName() {
    return countryName;
  }

  /**
   * Sets country name.
   *
   * @param countryName the country name
   */
  public void setCountryName(@Size(max = 100) String countryName) {
    this.countryName = countryName;
  }

  /**
   * Gets country code.
   *
   * @return the country code
   */
  public CountryCodeType getCountryCode() {
    return countryCode;
  }

  /**
   * Sets country code.
   *
   * @param countryCode the country code
   */
  public void setCountryCode(CountryCodeType countryCode) {
    this.countryCode = countryCode;
  }
}
