package com.subaru.cv.vehicleservice.controller;

import static com.subaru.cv.vehicleservice.utility.TestVinModel.testVinModel;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.Dcm;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.service.DcmService;
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

@WebMvcTest(controllers = {DcmController.class, com.subaru.cv.vehicleservice.api.DcmApi.DcmApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class DcmControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private final DcmService dcmService;

  @Autowired
  public DcmControllerTest(
      final MockMvc mockMvc,
      final DcmService dcmService,
      final ObjectMapper objectMapper
  ) {
    this.mockMvc = mockMvc;
    this.dcmService = dcmService;
    this.objectMapper = objectMapper;
  }

  @Test
  void test_getDcmByDcmId_validDcmId_okAndJson() throws Exception {
    var dcmId = Instancio.gen().longs().get();
    var dcm = Instancio.create(Dcm.class);
    var expectedResponse = objectMapper.writeValueAsString(dcm);

    // Arrange
    when(dcmService
        .getDcmByDcmId(dcmId))
        .thenReturn(Optional.of(dcm));

    // Act
    var restResponse = this.mockMvc.perform(
        get("/v1/dcm/{dcmId}", dcmId)
            .accept(MediaType.APPLICATION_JSON)
    );

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmByVin_validVin_okAndJson() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcm = Instancio.create(Dcm.class);
    var expectedResponse = objectMapper.writeValueAsString(dcm);

    when(dcmService
        .getDcmByVin(vin))
        .thenReturn(Optional.of(dcm));

    // Act
    var restResponse =
        this.mockMvc.perform(
            get("/v1/dcm")
                .header("vin", vin)
                .accept(MediaType.APPLICATION_JSON)
        );

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmByVin_invalidVin_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(16)
        .alphaNumeric()
        .get();

    // Act
    var restResponse =
        this.mockMvc.perform(
            get("/v1/dcm")
                .queryParam("vin", vin)
                .accept(MediaType.APPLICATION_JSON)
        );

    // Assert
    restResponse
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_getDcmByVin_invalidQueryParameter_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcm = Instancio.create(Dcm.class);

    when(dcmService
        .getDcmByVin(vin))
        .thenReturn(Optional.of(dcm));

    // Act
    var restResponse =
        this.mockMvc.perform(
            get("/v1/dcm")
                .queryParam("vin", vin)
                .accept(MediaType.APPLICATION_JSON)
        );

    // Assert
    restResponse
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_getDcmByVin_missingQueryParameter_badRequest() throws Exception {
    // Arrange
    // Act
    var restResponse =
        this.mockMvc.perform(
            get("/v1/dcm")
                .accept(MediaType.APPLICATION_JSON)
        );

    // Assert
    restResponse
        .andExpect(status().isBadRequest());
  }
}
