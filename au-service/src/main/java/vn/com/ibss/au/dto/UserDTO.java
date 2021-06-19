package vn.com.ibss.au.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.ibss.au.validation.PasswordMatches;
import vn.com.ibss.au.validation.ValidEmail;
import vn.com.ibss.au.validation.ValidPassword;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
public class UserDTO {

    private Integer id;
    private String username;
    @ValidPassword
    private String password;
    @ValidEmail
    private String email;
    private List<Integer> roles;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private String firstName;
    private String lastName;
    private Integer deleted;


}
