package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.core.exception.CafeInternalServerErrorException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ModelYearConverter implements AttributeConverter<Integer, String> {

  /**
   * Converts a model year to a String for database storage.
   *
   * @param attribute the model year as a Long
   * @return the model year as a String
   */
  @Override
  public String convertToDatabaseColumn(Integer attribute) {
    return attribute.toString();
  }

  /**
   * Converts a String from the database to a model year.
   * Necessary because the database stores model year as a String,
   * sometimes with the format "2024" and other times with "2024.0".
   *
   * @param dbData the model year as a String from the database
   * @return the model year as an Integer
   * @throws CafeInternalServerErrorException if the String cannot be parsed to a Long
   */
  @Override
  public Integer convertToEntityAttribute(String dbData) {
    try {
      return Double.valueOf(dbData).intValue();
    } catch (NumberFormatException e) {
      throw new CafeInternalServerErrorException("Data integrity issue--invalid model year format: " + dbData);
    }
  }
}
