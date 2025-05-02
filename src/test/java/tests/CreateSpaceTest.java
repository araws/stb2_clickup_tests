package tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import requests.space.CreateSpaceRequests;
import requests.space.DeleteSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "My space from Java";

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final Response response = CreateSpaceRequests.createSpace(space);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);

        final String spaceId = response.jsonPath().getString("id");

        final Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

    }
}
