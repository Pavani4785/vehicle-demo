package com.subaru.cv.vehicleservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.BaseCapability;
import com.subaru.cv.vehicleservice.api.CapabilityApi.CapabilityApiController;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.service.CapabilityService;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {CapabilityController.class, CapabilityApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class CapabilityControllerTest {

  private final MockMvc mockMvc;

  @MockBean
  private final CapabilityService capabilityService;

  private final ObjectMapper objectMapper;

  @Autowired
  CapabilityControllerTest(
      final MockMvc mockMvc,
      final CapabilityService capabilityService,
      final ObjectMapper objectMapper
  ) {
    this.mockMvc = mockMvc;
    this.capabilityService = capabilityService;
    this.objectMapper = objectMapper;
  }

  @Test
  void test_getCapability_validCapabilityCode_okAndJson() throws Exception {
    // Arrange
    String capabilityCode = Instancio.gen().string().get();
    var capability = Instancio.create(BaseCapability.class);
    var expectedResponse = objectMapper.writeValueAsString(capability);

    when(capabilityService
        .getBaseCapability(capabilityCode))
        .thenReturn(Optional.of(capability));

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/capabilities/{capabilityCode}", capabilityCode)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getCapability_nonExistentCapabilityCode_notFound() throws Exception {
    // Arrange
    String capabilityCode = Instancio.gen().string().get();
    var expectedResponse = objectMapper.writeValueAsString(
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Capability not found"));

    when(capabilityService
        .getBaseCapability(capabilityCode))
        .thenReturn(Optional.empty());

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/capabilities/{capabilityCode}", capabilityCode)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isNotFound())
        .andExpect(content().json(expectedResponse));

  }

  @Test
  void test_listCapabilities_validRequest_okAndJson() throws Exception {
    var capabilitySet = Instancio.createSet(BaseCapability.class);
    var expectedResponse = objectMapper.writeValueAsString(capabilitySet);

    // Arrange
    when(capabilityService
        .getBaseCapabilities())
        .thenReturn(capabilitySet);

    // Act
    var restResponse = mockMvc.perform(
        get("/v1/capabilities")
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    restResponse
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }
}
