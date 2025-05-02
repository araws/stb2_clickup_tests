package requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import properties.ClickUpProperties;
import requests.BaseRequest;
import url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {

    public static Response deleteSpace(String spaceId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
