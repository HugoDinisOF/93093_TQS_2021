package pt.ua.ex02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    public ResponseEntity<Car> createCar(Car car){

    }

    public List<Car> getAllCars(){

    }

    public  ResponseEntity<Car> getCarById(Long id){

    }
}
