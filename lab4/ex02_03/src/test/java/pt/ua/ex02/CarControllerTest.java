package pt.ua.ex02;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService carManagerService;

    @Test
    public void getCar_Test() throws Exception {
        given( carManagerService.getCarDetails(Mockito.anyLong())).willReturn( new Car("tesla", "model 3"));

        mvc.perform(MockMvcRequestBuilders.get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("model").value("model 3"))
                .andExpect(jsonPath("maker").value("tesla"));

        verify(carManagerService, times(1)).getCarDetails(Mockito.anyLong());

    }


    @Test
    public void postCar_Test() throws Exception {
        when(carManagerService.save(any())).thenReturn(new Car("tesla", "model 3"));

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(new Car("model 3", "tesla"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("model 3")))
                .andExpect(jsonPath("$.maker", is("tesla")));

        verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void getAllCars_Test() throws Exception {
        Car car1 = new Car("tesla", "model 3");
        Car car2 = new Car("renault", "megane");
        Car car3 = new Car("opel", "corsa");
        List<Car> allCars = Arrays.asList(car1, car2, car3);

        given(carManagerService.getAllCars()).willReturn(allCars);

        mvc.perform(MockMvcRequestBuilders.get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(car1.getModel())))
                .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(car3.getMaker())));

        verify(carManagerService, VerificationModeFactory.times(1)).getAllCars();

    }
}