package com.subaru.cv.vehicleservice.controller;

import static com.subaru.cv.vehicleservice.utility.TestVinModel.testVinModel;
import static org.instancio.Select.field;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subaru.cv.model.GetVehiclesByVinsRequest;
import com.subaru.cv.model.Vehicle;
import com.subaru.cv.vehicleservice.configuration.TestRestConfiguration;
import com.subaru.cv.vehicleservice.service.VehicleService;
import com.subaru.cv.vehicleservice.utility.TestVin;
import java.util.Optional;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {VehicleController.class, com.subaru.cv.vehicleservice.api.VehicleApi.VehicleApiController.class})
@WithMockUser(username = "test_user", roles = {"CV_MYSUBARU_USER"})
@Import({TestRestConfiguration.class})
class VehicleControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private final VehicleService vehicleService;

  @Autowired
  VehicleControllerTest(
      final MockMvc mockMvc,
      final ObjectMapper objectMapper,
      final VehicleService vehicleService
  ) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.vehicleService = vehicleService;
  }

  @Test
  void test_getVehicleByVin_validVin_okAndJson() throws Exception {
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var vehicle = Instancio.create(Vehicle.class);

    var expectedResponse = objectMapper.writeValueAsString(vehicle);

    // Arrange
    when(vehicleService
        .getVehicleByVin(vin))
        .thenReturn(Optional.of(vehicle));

    // Act
    var response = mockMvc.perform(
        get("/v1/vehicles")
            .header("vin", vin)
            .accept(MediaType.APPLICATION_JSON));
    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void test_getVehicleByVin_vinInPath_notFound() throws Exception {
    var vin = Instancio.of(testVinModel)
        .create()
        .getVinString();

    var vehicle = Instancio.create(Vehicle.class);

    // Arrange
    when(vehicleService
        .getVehicleByVin(vin))
        .thenReturn(Optional.of(vehicle));

    // Act
    var response = mockMvc.perform(
        get("/v1/vehicles/{vin}", vin)
    );

    // Assert
    response
        .andExpect(status().isNotFound());
  }

  @Test
  void test_getVehiclesByListOfVin_validVins_okAndJson() throws Exception {
    // Arrange
    var vins = Instancio.ofSet(testVinModel)
        .create()
        .stream()
        .map(TestVin::getVinString)
        .collect(Collectors.toUnmodifiableSet());

    var request = Instancio.of(GetVehiclesByVinsRequest.class)
        .set(field("vins"), vins)
        .create();

    var vehicles = Instancio.createSet(Vehicle.class);

    var expectedResponse = objectMapper.writeValueAsString(vehicles);

    when(vehicleService
        .getVehiclesByVins(vins))
        .thenReturn(vehicles);

    // Act
    var response = mockMvc.perform(
        post("/v1/vehicles")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    // Assert
    response
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(expectedResponse));
  }
}
