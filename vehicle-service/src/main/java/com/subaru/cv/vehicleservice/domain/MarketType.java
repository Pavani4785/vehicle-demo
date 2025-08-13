package com.subaru.cv.vehicleservice.domain;

/**
 * Represents different markets as combinations of countries and organizations.
 */
public enum MarketType {
  /**
   * Subaru of America USA market.
   */
  SOA_USA(1, CountryCodeType.USA, OrganizationType.SOA),
  /**
   * Subaru of Canada Canadian market.
   */
  SCI_CAN(2, CountryCodeType.CAN, OrganizationType.SCI),
  /**
   * Subaru Japan market.
   */
  SBR_JPN(3, CountryCodeType.JPN, OrganizationType.SBR),
  /**
   * Toyota Motor North America USA market.
   */
  TMNA_USA(4, CountryCodeType.USA, OrganizationType.TMNA),
  /**
   * Toyota Motor North America Canadian market.
   */
  TMNA_CAN(5, CountryCodeType.CAN, OrganizationType.TMNA),
  /**
   * Guam market.
   */
  GUM_MARKET(6, CountryCodeType.GUM, OrganizationType.GUM),
  /**
   * Hawaii market.
   */
  HAW_MARKET(7, CountryCodeType.HAW, OrganizationType.HAW),
  /**
   * Puerto Rico market.
   */
  PRI_MARKET(8, CountryCodeType.PRI, OrganizationType.PRI);

  private final int marketId;
  private final CountryCodeType countryCodeType;
  private final OrganizationType orgType;


  MarketType(
      final int marketId,
      final CountryCodeType countryCodeType,
      final OrganizationType orgType
  ) {
    this.marketId = marketId;
    this.countryCodeType = countryCodeType;
    this.orgType = orgType;
  }

  /**
   * Returns the MarketType corresponding to the given country and organization types.
   *
   * @param countryCodeType the country type
   * @param orgType     the organization type
   * @return the MarketType corresponding to the country and organization
   */
  @SuppressWarnings("unused")
  public static MarketType fromCountryAndOrg(
      final CountryCodeType countryCodeType,
      final OrganizationType orgType
  ) {
    for (MarketType marketType : values()) {
      if (marketType.getCountryType() == countryCodeType && marketType.getOrgType() == orgType) {
        return marketType;
      }
    }
    throw new IllegalArgumentException("Invalid combination of country type and organization: %s, %s".formatted(countryCodeType, orgType));
  }

  /**
   * Returns the MarketType corresponding to the given market ID.
   *
   * @param marketId the market ID
   * @return the MarketType corresponding to the market ID
   */
  @SuppressWarnings("unused")
  public static MarketType fromMarketId(final int marketId) {
    for (MarketType marketType : values()) {
      if (marketType.getMarketId() == marketId) {
        return marketType;
      }
    }
    throw new IllegalArgumentException("Invalid market ID: " + marketId);
  }

  /**
   * Returns the market ID associated with this MarketType.
   *
   * @return the market ID
   */
  public int getMarketId() {
    return marketId;
  }

  /**
   * Returns the CountryType associated with this MarketType.
   *
   * @return the country type
   */
  public CountryCodeType getCountryType() {
    return countryCodeType;
  }

  /**
   * Returns the OrganizationType associated with this MarketType.
   *
   * @return the organization type
   */
  public OrganizationType getOrgType() {
    return orgType;
  }
}
