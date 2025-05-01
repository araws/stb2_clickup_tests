package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import properties.ClickUpProperties;
import url.ClickUpUrl;

import static io.restassured.RestAssured.given;

class CreateSpaceTest {

    @Test
    void createSpaceTest(){

        JSONObject space = new JSONObject();
        space.put("name", "My space from Java");

        final Response response = given()
                .header("Authorization", ClickUpProperties.getToken())
                .contentType(ContentType.JSON)
                .body(space.toString())
                .when()
                .post(ClickUpUrl.getBaseUrl() + "/team/90151123937/space")
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("My space from Java");

    }
}
