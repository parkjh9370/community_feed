package main.admin.ui.query;

import java.util.List;
import main.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
