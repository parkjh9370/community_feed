package main.acceptance.steps;

import io.restassured.RestAssured;
import main.auth.application.dto.CreateUserAuthRequestDto;
import main.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/send-verification-email")
            .then()
            .extract()
            .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
            .given()
            .queryParam("email", email)
            .queryParam("token", token)
            .when()
            .get("/signup/verify-token")
            .then()
            .statusCode(200) // 200 상태 코드 기대
            .extract()
            .jsonPath().getInt("code");
    }

    public static Integer registerUser(CreateUserAuthRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/register")
            .then()
            .extract()
            .jsonPath().get("code");
    }
}
