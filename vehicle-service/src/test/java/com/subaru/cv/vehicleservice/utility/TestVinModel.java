package com.subaru.cv.vehicleservice.utility;

import static org.instancio.Select.field;

import org.instancio.Instancio;
import org.instancio.Model;

/**
 * Provides a model for generating TestVin objects with a custom generator for the VIN string.
 */
public class TestVinModel {
  /**
   * a model for generating TestVin objects with a custom generator for the VIN string.
   */
  public static Model<TestVin> testVinModel = Instancio.of(TestVin.class)
      .supply(field(TestVin::getVinString), new InstancioTestVinGenerator())
      .toModel();
}
