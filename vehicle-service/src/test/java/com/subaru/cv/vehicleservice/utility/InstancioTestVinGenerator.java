package com.subaru.cv.vehicleservice.utility;

import org.instancio.Random;
import org.instancio.generator.AfterGenerate;
import org.instancio.generator.Generator;
import org.instancio.generator.Hints;

public class InstancioTestVinGenerator implements Generator<String> {

  @Override
  public String generate(Random random) {
    // Valid VIN characters (uppercase letters excluding I, O, Q and digits)
    String validChars = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
    StringBuilder vin = new StringBuilder(17);

    // Generate a 17-character VIN
    for (int i = 0; i < 17; i++) {
      int randomIndex = random.intRange(0, validChars.length() - 1);
      vin.append(validChars.charAt(randomIndex));
    }

    return vin.toString();
  }

  @Override
  public Hints hints() {
    return Hints.afterGenerate(AfterGenerate.DO_NOT_MODIFY);
  }
}
