package tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import requests.space.CreateSpaceRequests;

class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", "My space from Java");

        final Response response = CreateSpaceRequests.createSpace(space);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("My space from Java");

    }
}
