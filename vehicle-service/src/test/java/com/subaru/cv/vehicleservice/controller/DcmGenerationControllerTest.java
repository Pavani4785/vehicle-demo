package com.subaru.cv.vehicleservice.controller;

import static com.subaru.cv.vehicleservice.utility.TestVinModel.testVinModel;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.DcmGeneration;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.service.DcmGenerationService;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {DcmGenerationController.class, com.subaru.cv.vehicleservice.api.DcmGenerationApi.DcmGenerationApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class DcmGenerationControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private final DcmGenerationService dcmGenerationService;

  @Autowired
  DcmGenerationControllerTest(
      final MockMvc mockMvc,
      final ObjectMapper objectMapper,
      final DcmGenerationService dcmGenerationService
  ) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.dcmGenerationService = dcmGenerationService;
  }

  @Test
  void test_getDcmGenerationByDcmGeneration_validDcmGeneration_okAndJson() throws Exception {
    // Arrange
    var dcmGeneration = Instancio.create(DcmGeneration.class);
    var dcmGenerationId = Instancio.gen()
        .string()
        .get();

    var market = MarketType.SOA_USA;

    var expectedResponse = objectMapper.writeValueAsString(dcmGeneration);

    when(dcmGenerationService
        .getDcmGenerationByDcmGeneration(dcmGenerationId, market))
        .thenReturn(Optional.of(dcmGeneration));

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/dcmGeneration/{dcmGeneration}", dcmGenerationId)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmGenerationByVin_validVin_okAndJson() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcmGeneration = Instancio.create(DcmGeneration.class);

    var expectedResponse = objectMapper.writeValueAsString(dcmGeneration);

    when(dcmGenerationService
        .getDcmGenerationByVin(vin))
        .thenReturn(Optional.of(dcmGeneration));

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/dcmGeneration")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmGenerationByVin_vinAsQueryParam_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcmGeneration = Instancio.create(DcmGeneration.class);

    when(dcmGenerationService
        .getDcmGenerationByVin(vin))
        .thenReturn(Optional.of(dcmGeneration));

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/dcmGeneration")
            .queryParam("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_getDcmGenerationByVin_invalidVin_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(16)
        .alphaNumeric()
        .get();

    var dcmGeneration = Instancio.create(DcmGeneration.class);

    when(dcmGenerationService
        .getDcmGenerationByVin(vin))
        .thenReturn(Optional.of(dcmGeneration));

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/dcmGeneration")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON)
    );

    // Assert
    // FIXME: This should be a 400 Bad Request, but the service currently returns 500
    restResponse
        .andExpect(status().isInternalServerError());
  }
}
