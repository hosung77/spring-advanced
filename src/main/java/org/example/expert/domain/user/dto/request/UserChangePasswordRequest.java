package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(max=8,message = "비밀번호는 8자 미만이여야 합니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).*$")
    private String newPassword;
}
