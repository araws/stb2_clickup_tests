package tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.list.CreateListRequest;
import requests.space.CreateSpaceRequests;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "SPACE E2E";
    private static String listName = "TASKS";
    private String spaceId;
    private String listId;

    @Test
    void updateTaskE3ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", spaceName);

        final Response response = CreateSpaceRequests.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);

        return jsonData.getString("id");
    }

    private String createListStep() {
        JSONObject json = new JSONObject();
        json.put("name", listName);

        final Response response = CreateListRequest.createList(json, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");
    }
}
