package main.acceptance.steps;

import io.restassured.RestAssured;
import main.auth.application.dto.LoginRequestDto;
import main.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String requestLoginGetToken(LoginRequestDto dto) {
        UserAccessTokenResponseDto responseDto = RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/login")
            .then()
            .extract()
            .jsonPath()
            .getObject("value", UserAccessTokenResponseDto.class);

        return responseDto.accessToken();
    }

    public static Integer requestLoginGetResponseCode(LoginRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/login")
            .then()
            .extract()
            .jsonPath()
            .getObject("code", Integer.class);
    }
}
