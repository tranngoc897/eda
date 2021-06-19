package vn.com.ibss.au.mapper;

import org.springframework.stereotype.Component;
import vn.com.ibss.au.dto.RoleDTO;
import vn.com.ibss.au.entity.Role;
import vn.ibss.common.mapper.EntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RoleMapper extends EntityMapper<Role, RoleDTO, Integer> {

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public RoleDTO convertToDTO(Role role) {

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setRemark(role.getRemark());
        roleDTO.setDescription(role.getRemark());

        return roleDTO;
    }

    @Override
    public Role convertToEntity(Role entity, RoleDTO dto) {
        return null;
    }

    public List<RoleDTO> convertToDTOList(List<Role> roles) {

        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            RoleDTO roleDTO = convertToDTO(role);
            roleDTOS.add(roleDTO);
        }

        return roleDTOS;
    }
}
