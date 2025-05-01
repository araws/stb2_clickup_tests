package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreateSpaceTest {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TOKEN = "pk_188628807_JAPU86AGT51VKFO006SZOQ2I232GLR4Q";

    @Test
    void createSpaceTest(){

        JSONObject space = new JSONObject();
        space.put("name", "My space from Java");

        final Response response = given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(space.toString())
                .when()
                .post(BASE_URL + "/team/90151123937/space")
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("My space from Java");

    }
}
