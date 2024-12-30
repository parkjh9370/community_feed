package main.admin.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.admin.ui.dto.GetDailyRegisterUserResponseDto;
import main.admin.ui.query.UserStatsQueryRepository;
import main.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;

    @GetMapping("index")
    public Response<List<GetDailyRegisterUserResponseDto>> getDailyRegisterUser() {
        List<GetDailyRegisterUserResponseDto> dailyRegisterUserStats = userStatsQueryRepository.getDailyRegisterUserStats(
            7);
        return Response.ok(dailyRegisterUserStats);
    }
}
