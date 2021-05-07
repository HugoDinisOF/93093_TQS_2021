package pt.ua.ex02;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@WebMvcTest(CarController.class)
public class MockMvcTest {
    @MockBean
    private CarManagerService carManagerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
    @Test
    public void postCarTest() throws Exception {
        when(carManagerService.save(any())).thenReturn(new Car("tesla", "model 3"));

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(JsonUtil.toJson(new Car("tesla", "model 3")))
                .when()
                .post("/api/cars")
                .then()
                .log().all().statusCode(201)
                .body("model", is("model 3"))
                .body("maker", is("tesla"));


        verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void getAllCarsTest() throws Exception {
        Car car1 = new Car("tesla", "model 3");
        Car car2 = new Car("mercedes", "class a");
        Car car3 = new Car("opel", "corsa");
        List<Car> allCars = Arrays.asList(car1, car2, car3);

        given(carManagerService.getAllCars()).willReturn(allCars);

        RestAssuredMockMvc.given().auth().none().when().get("/api/cars").then()
                .log().all().statusCode(200)
                .body("$", hasSize(3))
                .body("[0].model", is(car1.getModel()))
                .body("[1].maker", is(car2.getMaker()))
                .body("[2].maker", is(car3.getMaker()));
        verify(carManagerService, VerificationModeFactory.times(1)).getAllCars();

    }
}
