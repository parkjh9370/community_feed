package main.user.ui;

import lombok.RequiredArgsConstructor;
import main.common.ui.Response;
import main.user.application.UserRelationService;
import main.user.application.dto.FollowUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }
}
