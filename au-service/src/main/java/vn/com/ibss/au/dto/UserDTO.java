package vn.com.ibss.au.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
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
