package vn.com.ibss.au.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.com.ibss.au.dto.RoleDTO;
import vn.com.ibss.au.dto.RoleRequestDTO;
import vn.com.ibss.au.entity.Role;
import vn.com.ibss.au.service.RoleService;
import vn.ibss.common.base.BaseController;
import vn.ibss.common.utils.AppConstants;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
@Api(value = "roles")
public class RoleController extends BaseController {

    @Autowired
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

}
