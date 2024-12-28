package main.auth.ui;

import lombok.RequiredArgsConstructor;
import main.auth.application.dto.SendEmailRequestDto;
import main.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(
        @RequestBody SendEmailRequestDto dto
    ) {
        return Response.ok(null);
    }
}
