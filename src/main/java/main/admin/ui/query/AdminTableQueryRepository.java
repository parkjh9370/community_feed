package main.admin.ui.query;

import main.admin.ui.dto.GetTableListResponse;
import main.admin.ui.dto.users.GetUserTableRequestDto;
import main.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
}
