package requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import properties.ClickUpProperties;
import requests.BaseRequest;
import url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequests {

    public static Response createSpace(JSONObject space) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .post(ClickUpUrl.getSpacesUrl(ClickUpProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
