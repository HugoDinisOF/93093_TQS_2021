a)
EmployeeRepositoryTest:

assertThat( found ).isEqualTo(alex); (line 27)
assertThat(fromDb).isNull(); (line 33)
assertThat(fromDb).isNotNull(); (line 42)
assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail()); (line 43)
assertThat(fromDb).isNull(); (line 49)
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName()); (line 65)

EmployeeRestControllerTemplateIT:

assertThat(found).extracting(Employee::getName).containsOnly("bob"); (line 51)
assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); (line 64)
assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex"); (line 65)

EmployeeService_UnitTest:

assertThat(found.getName()).isEqualTo(name); (line 53)\ assertThat(fromDb).isNull(); (line 59)
assertThat(doesEmployeeExist).isEqualTo(true); (line 67)
assertThat(doesEmployeeExist).isEqualTo(false); (line 75)\ assertThat(fromDb.getName()).isEqualTo("john"); (line 83)
assertThat(fromDb).isNull(); (line 92)
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName()); (line 103)



EmployeeRestControllerIT:

assertThat(found).extracting(Employee::getName).containsOnly("bob"); (line 51)

b)

Testing the EmployeeService with the EmployeeService_UnitTest.java:
whenValidName_thenEmployeeShouldBeFound()
whenInValidName_thenEmployeeShouldNotBeFound()
whenValidName_thenEmployeeShouldExist()
whenNonExistingName_thenEmployeeShouldNotExist()
whenValidId_thenEmployeeShouldBeFound()
whenInValidId_thenEmployeeShouldNotBeFound()
given3Employees_whengetAll_thenReturn3Records()

c)

@MockBean:
Is an annotation included in the spring-boot-test library that already includes Mockito.
Adds mock objects to the Spring application context.
The mock will replace any existing bean of the same type in the application context.
If no bean of the same type is defined, a new one will be added.
This annotation is useful in integration tests where a particular bean like an external service needs to be mocked.
Injects the mock into the Spring ApplicationContext.

@Mock:
Is an annotation from the Mockito library.
Allows us to create a mock object of a class or an interface.
Then, we can use the mock to stub return values for its methods and verify if they were called.
Doesn't inject the mock into the Spring ApplicationContext.

d)
.properties are used to keep a number of properties in a single file to run the application in a different environment.
In this case, application-integrationtest.properties is used to configure the connection properties datasource to adapt the integration test to use a real database.