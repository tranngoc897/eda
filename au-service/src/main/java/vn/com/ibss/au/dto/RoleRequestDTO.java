package vn.com.ibss.au.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class RoleRequestDTO {

    private Integer id;
    private String name;
    private String description;
    private String remark;


}
