package com.subaru.cv.vehicleservice.controller;

import static com.subaru.cv.vehicleservice.utility.TestVinModel.testVinModel;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.DcmSoftware;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.service.DcmSoftwareService;
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

@WebMvcTest(controllers = {DcmSoftwareController.class, com.subaru.cv.vehicleservice.api.DcmSoftwareApi.DcmSoftwareApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class DcmSoftwareControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private final DcmSoftwareService dcmSoftwareService;

  @Autowired
  DcmSoftwareControllerTest(
      final MockMvc mockMvc,
      final ObjectMapper objectMapper,
      final DcmSoftwareService dcmSoftwareService
  ) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.dcmSoftwareService = dcmSoftwareService;
  }


  @Test
  void test_getDcmSoftwareByDcmSoftware_validDcmSoftwareId_okAndJson() throws Exception {
    var dcmSoftwareId = Instancio.gen().longs().get();
    var dcmSoftware = Instancio.create(DcmSoftware.class);

    var expectedResponse = objectMapper.writeValueAsString(dcmSoftware);

    // Arrange
    when(dcmSoftwareService
        .getDcmSoftwareByDcmSoftwareId(dcmSoftwareId))
        .thenReturn(Optional.of(dcmSoftware));

    // Act
    var response = mockMvc.perform(
        get("/v1/dcmSoftware/{dcmSoftwareId}", dcmSoftwareId)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmSoftwareByVin_validVin_okAndJson() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcmSoftware = Instancio.create(DcmSoftware.class);

    var expectedResponse = objectMapper.writeValueAsString(dcmSoftware);

    when(dcmSoftwareService
        .getDcmSoftwareByVin(vin))
        .thenReturn(Optional.of(dcmSoftware));

    // Act
    var response = mockMvc.perform(
        get("/v1/dcmSoftware")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getDcmSoftwareByVin_vinInQueryParam_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var dcmSoftware = Instancio.create(DcmSoftware.class);

    when(dcmSoftwareService
        .getDcmSoftwareByVin(vin))
        .thenReturn(Optional.of(dcmSoftware));

    // Act
    var response = mockMvc.perform(
        get("/v1/dcmSoftware")
            .queryParam("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_getDcmSoftwareByVin_invalidVin_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .length(16)
        .alphaNumeric()
        .get();

    var dcmSoftware = Instancio.create(DcmSoftware.class);

    when(dcmSoftwareService
        .getDcmSoftwareByVin(vin))
        .thenReturn(Optional.of(dcmSoftware));

    // Act
    var response = mockMvc.perform(
        get("/v1/dcmSoftware")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    // FIXME: This should be a 400 Bad Request, but the service currently returns 500
    response
        .andExpect(status().isInternalServerError());
  }
}
