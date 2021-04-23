package books;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookSearchStepDef {
    Library library = new Library();
    List<Book> result = new ArrayList<>();


    @ParameterType("([0-9]{2})-([0-9]{2})-([0-9]{4})")
    public LocalDateTime iso8601DateMonthString(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }


    @ParameterType("([0-9]{2})-([0-9]{2})-([0-9]{4})")
    public LocalDateTime iso8601Date(String day, String month, String year){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        result = library.findBooks(Date.from(from.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant()), Date.from(to.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant()));
    }

    @Given("(a/another) book with the title {string}, written by {string}, published in {iso8601Date}")
    public void aBookWithTheTitleOneGoodBookWrittenByAnonymousPublishedInMarch(String title, String author, LocalDateTime published) {
        Book book = new Book(title, author, Date.from(published.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant()));
        library.addBook(book);
    }

    @Then("{int} books should have been found")
    public void booksShouldHaveBeenFound(int arg0) {
        assertEquals(arg0,result.size());
    }

    @And("Book {int} should have the title {string}")
    public void bookShouldHaveTheTitleSomeOtherBook(int position,String title) {
        assertEquals(title,result.get(position-1).getTitle());
    }
}
