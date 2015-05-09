package tchepannou.mora.rest.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.service.RoleService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController extends BaseRestController{
    //-- Attributes
    @Autowired
    private RoleService roleService;

    //-- Public
    @RequestMapping(value="/roles", method = RequestMethod.GET)
    public List<EnumDto> all (){
        return roleService.findAll().stream()
                .map(role -> new EnumDto.Builder().withEnum(role).build())
                .collect(Collectors.toList());
    }


    @RequestMapping(value="/roles/{roleId}", method = RequestMethod.GET)
    public EnumDto get (@PathVariable long roleId) {
        Role role = roleService.findById(roleId);
        if (role == null){
            throw new NotFoundException(roleId);
        }
        return new EnumDto.Builder().withEnum(role).build();
    }
}

