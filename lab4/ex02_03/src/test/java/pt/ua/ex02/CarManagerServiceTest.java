package pt.ua.ex02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;



    @BeforeEach
    public void setUp(){
        Car car1 = new Car("tesla", "model y");
        car1.setCarId(111l);
        Car car2 = new Car("porsche", "911");
        Car car3 = new Car("ferrari", "f40");
        List<Car> allcars = Arrays.asList(car1, car2, car3);
        Mockito.when(carRepository.findByCarId(car1.getCarId())).thenReturn(car1);
        Mockito.when(carRepository.findByCarId(-9)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allcars);
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car found = carManagerService.getCarDetails(111l);
        assertThat(found.getModel()).isEqualTo("model y");
    }

    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        Car fromDb = carRepository.findByCarId(-9);
        assertThat(fromDb).isNull();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(-9);
    }

    @Test
    public void given3Employees_whenGetAll_thenReturn3Records() {
        Car car1 = new Car("tesla", "model y");
        Car car2 = new Car("porsche", "911");
        Car car3 = new Car("ferrari", "f40");
        List<Car> allcars2 = carRepository.findAll();
        assertThat(allcars2).hasSize(3).extracting(Car::getModel).contains(car1.getModel(), car2.getModel(), car3.getModel());
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }


}