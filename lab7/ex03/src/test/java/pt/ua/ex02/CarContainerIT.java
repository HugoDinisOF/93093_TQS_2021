package pt.ua.ex02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarContainerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @LocalServerPort
    int localPort;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres:12"))
            .withUsername("admin")
            .withPassword("admin")
            .withDatabaseName("test");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }


    @BeforeEach
    public void setUpTestCars() throws Exception {

    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
    @Test
    public void whenValidInput_thenCreateCar() {
        Car car = new Car("ferrari", "f40");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("f40");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        repository.save(new Car("renault", "zoe"));
        repository.save(new Car("toyota", "prius"));

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("zoe", "prius");
    }

    @Test
    void getCar_returnsCarDetailsTest() {
        Car x= new Car("ferrari", "f40");
        repository.save(x);
        ResponseEntity<Car> entity = restTemplate.getForEntity("/api/cars/"+x.getCarId(), Car.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getMaker()).isEqualTo("ferrari");
    }
}
