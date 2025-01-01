package main.admin.ui.query;

import main.admin.ui.dto.GetTableListResponse;
import main.admin.ui.dto.post.GetPostTableRequestDto;
import main.admin.ui.dto.post.GetPostTableResponseDto;
import main.admin.ui.dto.users.GetUserTableRequestDto;
import main.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTable(GetUserTableRequestDto dto);

    GetTableListResponse<GetPostTableResponseDto> getPostTable(GetPostTableRequestDto dto);
}
