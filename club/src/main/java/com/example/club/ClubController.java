package com.example.club;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ibss.common.base.BaseController;


@RestController
@RequestMapping(value = "/clubs")
@Api(value = "club")
public class ClubController extends BaseController {

   /* @Autowired
    private RoleService roleService;

    @ApiOperation(
            httpMethod = "GET",
            value = "GET role by ID",
            notes = "GET role by ID",
            response = RoleDTO.class
    )
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ')")
    //@PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Integer id) {
        return null;
    }
*/
}
