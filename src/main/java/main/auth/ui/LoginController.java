package main.auth.ui;

import lombok.RequiredArgsConstructor;
import main.auth.application.AuthService;
import main.auth.application.dto.LoginRequestDto;
import main.auth.application.dto.UserAccessTokenResponseDto;
import main.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(
        @RequestBody LoginRequestDto dto
    ) {
        return Response.ok(authService.login(dto));
    }
}
