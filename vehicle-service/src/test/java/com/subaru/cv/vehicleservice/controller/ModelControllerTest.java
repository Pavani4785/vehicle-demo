package com.subaru.cv.vehicleservice.controller;

import static com.subaru.cv.vehicleservice.utility.TestVinModel.testVinModel;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.Capability;
import com.subaru.cv.model.Model;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.domain.MarketType;
import com.subaru.cv.vehicleservice.service.CapabilityService;
import com.subaru.cv.vehicleservice.service.ModelService;
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

@WebMvcTest(controllers = {ModelController.class, com.subaru.cv.vehicleservice.api.ModelApi.ModelApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class ModelControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private final ModelService modelService;

  @MockBean
  private final CapabilityService capabilityService;

  @Autowired
  ModelControllerTest(
      final MockMvc mockMvc,
      final ObjectMapper objectMapper,
      final ModelService modelService,
      final CapabilityService capabilityService
  ) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.modelService = modelService;
    this.capabilityService = capabilityService;
  }

  @Test
  void test_getModelByModelKey_validModelKey_okAndJson() throws Exception {
    // Arrange
    var modelKey = Instancio.gen().string().get();
    var model = Instancio.create(Model.class);
    var market = MarketType.SOA_USA;

    var expectedResponse = objectMapper.writeValueAsString(model);

    when(modelService
        .getModelByModelKey(modelKey, market))
        .thenReturn(Optional.of(model));

    // Act
    var response = mockMvc.perform(
        get("/v1/models/{modelKey}", modelKey)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getModelByVin_validVin_okAndJson() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var model = Instancio.create(Model.class);

    var expectedResponse = objectMapper.writeValueAsString(model);

    when(modelService
        .getModelByVin(vin))
        .thenReturn(Optional.of(model));

    // Act
    var response = mockMvc.perform(
        get("/v1/models")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getModelByVin_vinInQueryParam_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var model = Instancio.create(Model.class);

    when(modelService
        .getModelByVin(vin))
        .thenReturn(Optional.of(model));

    // Act
    var response = mockMvc.perform(
        get("/v1/models")
            .queryParam("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_getModelByVin_invalidVin_badRequest() throws Exception {
    // Arrange
    var vin = Instancio.gen()
        .string()
        .alphaNumeric()
        .get();

    var model = Instancio.create(Model.class);

    when(modelService
        .getModelByVin(vin))
        .thenReturn(Optional.of(model));

    // Act
    var response = mockMvc.perform(
        get("/v1/models")
            .queryParam("vin", vin)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isBadRequest());
  }

  @Test
  void test_listModelCapabilities_validModelKey_okAndJson() throws Exception {
    // Arrange
    var modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var capabilitySet = Instancio.createSet(Capability.class);

    var expectedResponse = objectMapper.writeValueAsString(capabilitySet);

    when(capabilityService
        .getCapabilities(modelKey, market))
        .thenReturn(capabilitySet);

    // Act
    var response = mockMvc.perform(
        get("/v1/models/{modelKey}/capabilities", modelKey)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getCapabilityCodesByModelKey_validModelKey_okAndJson() throws Exception {
    // Arrange
    var capabilityCodes = Instancio.createSet(String.class);
    var modelKey = Instancio.gen().string().get();
    var market = MarketType.SOA_USA;
    var includeInactive = false; // Should default to false when not specified

    var expectedResponse = objectMapper.writeValueAsString(capabilityCodes);

    when(capabilityService
        .getCapabilityCodes(modelKey, market, includeInactive))
        .thenReturn(capabilityCodes);

    // Act
    var response = mockMvc.perform(
        get("/v1/models/{modelKey}/capabilityCodes", modelKey)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }
}
