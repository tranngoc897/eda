package vn.com.ibss.au.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

    private String oldPassword;

    private String newPassword;

}
