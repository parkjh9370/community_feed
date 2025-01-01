package main.admin.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.admin.ui.dto.GetTableListResponse;
import main.admin.ui.dto.post.GetPostTableRequestDto;
import main.admin.ui.dto.post.GetPostTableResponseDto;
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
        GetTableListResponse<GetUserTableResponseDto> userTable = adminTableQueryRepository.getUserTable(
            dto);
        return Response.ok(userTable);
    }

    @GetMapping("/posts")
    public Response<GetTableListResponse<GetPostTableResponseDto>> getPostTable(
        GetPostTableRequestDto dto
    ) {
        GetTableListResponse<GetPostTableResponseDto> postTable = adminTableQueryRepository.getPostTable(
            dto);

        return Response.ok(postTable);
    }
}
