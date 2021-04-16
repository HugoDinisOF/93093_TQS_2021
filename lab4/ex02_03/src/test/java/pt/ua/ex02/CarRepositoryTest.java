package pt.ua.ex02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCarByExistingId_thenReturnCar() {
        Car car = new Car("porsche", "911");
        entityManager.persistAndFlush(car);
        Car fromDb = carRepository.findById(car.getCarId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo( car.getModel());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }
    @Test
    public void givenSetOfCar_whenFindAll_thenReturnAllCar() {
        Car car1 = new Car("tesla", "model y");
        Car car2 = new Car("porsche", "911");
        Car car3 = new Car("ferrari", "f40");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allcars = carRepository.findAll();

        assertThat(allcars).hasSize(3).extracting(Car::getModel).contains(car1.getModel(), car2.getModel(), car3.getModel());
    }
}