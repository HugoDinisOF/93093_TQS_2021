import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


class Ex01Test {
    @Test
    void todoisavailabletest(){
        get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200);
    }

    @Test
    void individualapitest(){
        get("https://jsonplaceholder.typicode.com/todos/4")
                .then()
                .body("id",is(4))
                .body("title",is("et porro tempora"));
    }

    @Test
    void containapitest(){
        get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .assertThat()
                .body("id",hasItems(198,199));
    }
}