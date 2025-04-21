package org.example.expert.domain.manager.dto.response;

import lombok.Getter;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;

@Getter
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;

    public ManagerResponse(Long id, UserResponse user) {
        this.id = id;
        this.user = user;
    }

    public static ManagerResponse from(Manager manager){
        User user = manager.getUser();
        return new ManagerResponse(
                manager.getId(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }

}
