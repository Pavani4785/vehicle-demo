package com.subaru.cv.vehicleservice.mapper;

import com.subaru.cv.vehicleservice.domain.TeleMarketEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for {@link TeleMarketEntity}.
 */
@Component
public class MarketMapper {

  /**
   * Maps a {@link TeleMarketEntity} to a market code (e.g. SOA-USA).
   *
   * @param teleMarketEntity the market entity
   * @return the market code
   */
  public String toMarketCode(final TeleMarketEntity teleMarketEntity) {
    var orgName = teleMarketEntity.getOrg().getOrgName();
    var countryCode = teleMarketEntity.getCountry().getCountryCode();

    return "%s_%s".formatted(orgName, countryCode);
  }
}
