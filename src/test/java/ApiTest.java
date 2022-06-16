import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private final String COUNTRY_NAME = "WW";
    private final String ID_INCORRECT = "bad";
    private int createdCountryId;
    private int notExistCountryId;
    private int uniqueCountryId;


    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @BeforeEach
    public void createCountry() {
        createdCountryId = given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"" + COUNTRY_NAME + "\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .extract()
                .path("id");
        notExistCountryId = createdCountryId + 1000;
    }

    @AfterEach
    public void deleteCreatedCountry() {
        delete("/api/countries/" + createdCountryId);
        delete("/api/countries/" + uniqueCountryId);
    }


    @Nested
    @DisplayName("Post methods")
    class PostMethodsTest {

        @Test
        public void createUniqueCountry() {
            uniqueCountryId = given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"LA\"\n" +
                            "}")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(201)
                    .body("id", not(empty()))
                    .extract()
                    .path("id");
            when()
                    .get("/api/countries/" + uniqueCountryId)
                    .then()
                    .body("id", is(uniqueCountryId),
                            "countryName", is("LA"),
                            "locations", nullValue());
        }

        @Test
        public void creatingCountryNullName() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            " \"countryName\":" + null +
                            "}")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(400)
                    .body("title", is("Method argument not valid"),
                            "message", is("error.validation"),
                            "fieldErrors[0].objectName", is("country"),
                            "fieldErrors[0].field", is("countryName"),
                            "fieldErrors[0].message", is("must not be null"));
        }

        @Test
        public void createNotUniqueCountry() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"XX\"\n" +
                            "}")
                    .when()
                    .post("/api/countries");
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"XX\"\n" +
                            "}")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(500)
                    .body("title", is("Internal Server Error"));
        }

        @Test
        public void CreateCountryWithNameLessThanTwoCharactersLong() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"m\"\n" +
                            "}")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(400)
                    .body("title", is("Method argument not valid"),
                            "message", is("error.validation"),
                            "fieldErrors[0].objectName", is("country"),
                            "fieldErrors[0].field", is("countryName"),
                            "fieldErrors[0].message", is("size must be between 2 and 2"));
        }

        @Test
        public void creatingCountryWithNameLengthGreaterThenAcceptable() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"ppp\"\n" +
                            "}")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(400)
                    .body("title", is("Method argument not valid"),
                            "message", is("error.validation"),
                            "fieldErrors[0].objectName", is("country"),
                            "fieldErrors[0].field", is("countryName"),
                            "fieldErrors[0].message", is("size must be between 2 and 2"));
        }

        @Test
        public void creatingCountryWhenRequestBodyNotExist() {
            given()
                    .contentType("application/json")
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(400)
                    .body("title", is("Bad Request"),
                            "message", is("error.http.400"),
                            "detail", containsString("Required request body is missing"));
        }

    }

    @Nested
    @DisplayName("Get methods")
    class GetMethodsTest {

        @Test
        public void getCountryById() {
            when()
                    .get("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(200)
                    .body("id", not(empty()),
                            "countryName", is(COUNTRY_NAME),
                            "locations", nullValue());
        }

        @Test
        public void getCountryByNotExistingId() {
            when()
                    .get("/api/countries/" + notExistCountryId)
                    .then()
                    .statusCode(404)
                    .body("title", is("Not Found"));
        }

        @Test
        public void getCountryByIdIncorrect() {
            when()
                    .get("/api/countries/" + ID_INCORRECT)
                    .then()
                    .statusCode(400)
                    .body("title", is("Bad Request"));
        }

    }

    @Nested
    @DisplayName("Patch methods")
    class PatchMethodsTest {

        @Test
        public void patchingCountry() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + createdCountryId + "\",\n" +
                            "  \"countryName\": \"LA\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(200)
                    .body("id", is(createdCountryId),
                            "countryName", is("LA"),
                            "locations", nullValue());
            when()
                    .get("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(200)
                    .body("id", is(createdCountryId),
                            "countryName", is("LA"),
                            "locations", nullValue());
        }

        @Test
        public void patchingCountryWhenCountryIdNotExist() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + notExistCountryId + "\",\n" +
                            "  \"countryName\": \"PA\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + notExistCountryId)
                    .then()
                    .statusCode(400)
                    .body("title", is("Entity not found"),
                            "entityName", is("country"),
                            "errorKey", is("idnotfound"));
        }

        @Test
        public void patchingCountryWithNotEqualsIdsInBodyAndUrl() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + notExistCountryId + "\",\n" +
                            "  \"countryName\": \"PA\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(400)
                    .body("title", is("Invalid ID"),
                            "entityName", is("country"),
                            "errorKey", is("idinvalid"));
        }

        @Test
        public void patchingCountryWhenIdIsIncorrect() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + ID_INCORRECT + "\",\n" +
                            "  \"countryName\": \"PA\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + ID_INCORRECT)
                    .then()
                    .statusCode(400)
                    .body("title", is("Bad Request"));
        }

        @Test
        public void patchingCountryWhenIncorrectCountryName() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + createdCountryId + "\",\n" +
                            "  \"countryName\": \"PAT\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(500)
                    .body("detail", containsString("Could not commit JPA transaction"));
        }

        @Test
        public void patchingCountryWithAlreadyExistingCountryName() {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"countryName\": \"AE\"\n" +
                            "}")
                    .when()
                    .post("/api/countries");
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"id\": \"" + createdCountryId + "\",\n" +
                            "  \"countryName\": \"AE\"\n" +
                            "}")
                    .when()
                    .patch("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(500)
                    .body("detail", containsString("could not execute batch"));
        }

    }

    @Nested
    @DisplayName("Delete methods")
    class DeleteMethodsTest {

        @Test
        public void deleteCountry() {
            when()
                    .delete("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(204);
            when()
                    .get("/api/countries/" + createdCountryId)
                    .then()
                    .statusCode(404)
                    .body("title", is("Not Found"));
        }

        @Test
        public void deleteCountryWhenIdNotExist() {
            when()
                    .delete("/api/countries/" + notExistCountryId)
                    .then()
                    .statusCode(500)
                    .body("detail", is("No class com.tinkoff.edu.domain.Country entity with id " +
                            notExistCountryId + " exists!"));
        }

        @Test
        public void deleteCountryWhenIncorrectId() {
            when()
                    .delete("/api/countries/" + ID_INCORRECT)
                    .then()
                    .statusCode(400)
                    .body("title", is("Bad Request"));
        }

    }

}