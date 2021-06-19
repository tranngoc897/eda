package vn.com.ibss.au.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.ibss.au.validation.ValidPassword;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

    private String oldPassword;

    @ValidPassword
    private String newPassword;

}
