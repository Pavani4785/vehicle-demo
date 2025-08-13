package com.subaru.cv.vehicleservice.mapper;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.subaru.cv.vehicleservice.configuration.TestMapperConfiguration;
import com.subaru.cv.vehicleservice.domain.CountryCodeType;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.domain.OrganizationType;
import com.subaru.cv.vehicleservice.domain.TeleCountryEntity;
import com.subaru.cv.vehicleservice.domain.TeleMarketEntity;
import com.subaru.cv.vehicleservice.domain.TeleOrgEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = TestMapperConfiguration.class)
class MarketMapperTest {

  private final MarketMapper marketMapper = new MarketMapper();

  @Test
  void test_toMarketCode_validTeleMarketEntity_returnsMarketCode() {
    // Arrange
    var orgEntity = Instancio.of(TeleOrgEntity.class)
        .set(field(TeleOrgEntity::getOrgName), OrganizationType.SOA)
        .create();
    var countryEntity = Instancio.of(TeleCountryEntity.class)
        .set(field(TeleCountryEntity::getCountryCode), CountryCodeType.USA)
        .create();

    var teleMarketEntity = Instancio.of(TeleMarketEntity.class)
        .set(field(TeleMarketEntity::getOrg), orgEntity)
        .set(field(TeleMarketEntity::getCountry), countryEntity)
        .create();

    String expectedResult = MarketType.SOA_USA.toString();

    // Act
    String result = marketMapper.toMarketCode(teleMarketEntity);

    // Assert
    assertEquals(expectedResult, result);
  }
}
