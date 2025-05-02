package tests.space;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.space.CreateSpaceRequests;
import requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Create space with space name: {0}")
    @DisplayName("Create space with valid space name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final Response response = CreateSpaceRequests.createSpace(space);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(spaceName);

        final String spaceId = response.jsonPath().getString("id");

        final Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

    }

    private static Stream<Arguments> createSpaceData() {
        return Stream.of(
                Arguments.of("TEST SPACE"),
                Arguments.of("123"),
                Arguments.of("*")
        );
    }
}
