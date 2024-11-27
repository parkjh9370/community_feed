package main.user.application.dto;

import java.util.Objects;

public class CreateUserRequestDto {

    private final String name;
    private final String profileImageUrl;

    public CreateUserRequestDto(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUserRequestDto that = (CreateUserRequestDto) o;
        return Objects.equals(name, that.name) && Objects.equals(profileImageUrl,
            that.profileImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profileImageUrl);
    }
}
