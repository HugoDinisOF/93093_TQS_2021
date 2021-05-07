package pt.ua.ex02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class Ex02ApplicationTests {

    @Autowired
    StudentRepository repository;

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

    @Test
    void whenInsertOneStudent_then_Succeed(){
        Student student = new Student();
        student.setName("Hugo Ferreira");
        student.setAge(21);
        student.setCourse("LEI");
        student.setNmec(93093);
        student.setNationality("portuguese");
        repository.save(student);

        List<Student> students = repository.findAll();

        assertEquals(student.getName(),students.get(0).getName());

    }

}
