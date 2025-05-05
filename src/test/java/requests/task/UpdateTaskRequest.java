package requests.task;

import dto.request.UpdateTaskRequestDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import requests.BaseRequest;
import url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject updateTask, String taskId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(updateTask.toString())
                .when()
                .put(ClickUpUrl.getTaskUrl(taskId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().ifError()
                .extract()
                .response();
    }
}
