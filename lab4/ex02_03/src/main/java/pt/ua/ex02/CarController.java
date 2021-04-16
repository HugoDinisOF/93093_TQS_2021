package pt.ua.ex02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/cars")
    private ResponseEntity<Car> postCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved=carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    private List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    private Car getCar(@PathVariable long id) {
        return carManagerService.getCarDetails(id);
    }

}
