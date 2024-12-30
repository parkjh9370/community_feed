package main.admin.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.admin.ui.dto.GetTableListResponse;
import main.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import main.admin.ui.dto.users.GetUserTableRequestDto;
import main.admin.ui.dto.users.GetUserTableResponseDto;
import main.admin.ui.query.AdminTableQueryRepository;
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
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("index")
    public Response<List<GetDailyRegisterUserResponseDto>> getDailyRegisterUser() {
        List<GetDailyRegisterUserResponseDto> dailyRegisterUserStats = userStatsQueryRepository.getDailyRegisterUserStats(
            7);
        return Response.ok(dailyRegisterUserStats);
    }

    @GetMapping("/users")
    public Response<GetTableListResponse<GetUserTableResponseDto>> getUserTable(
        GetUserTableRequestDto dto
    ) {
        GetTableListResponse<GetUserTableResponseDto> userTableData = adminTableQueryRepository.getUserTableData(
            dto);
        return Response.ok(userTableData);
    }
}
