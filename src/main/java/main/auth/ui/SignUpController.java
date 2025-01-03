package main.auth.ui;

import lombok.RequiredArgsConstructor;
import main.auth.application.AuthService;
import main.auth.application.EmailService;
import main.auth.application.dto.CreateUserAuthRequestDto;
import main.auth.application.dto.SendEmailRequestDto;
import main.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(
        @RequestBody SendEmailRequestDto dto
    ) {
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-token")
    public Response<Void> verifyEmail(
        String email,
        String token
    ) {
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(
        @RequestBody CreateUserAuthRequestDto dto
    ) {
        return Response.ok(authService.registerUser(dto));
    }
}
