package tchepannou.mora.rest.auth.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.exception.RoleException;
import tchepannou.mora.core.exception.RoleNotFoundException;
import tchepannou.mora.core.service.RoleService;
import tchepannou.mora.rest.auth.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api (value="Roles", description = "Manage roles")
public class RoleController {
    //-- Attributes
    @Autowired
    private RoleService roleService;

    //-- Public
    @RequestMapping(value="/roles", method = RequestMethod.GET)
    @ApiOperation (value="Retrieve all Roles")
    public List<RoleDto> all (){
        List<Role> roles = roleService.findAll();

        List<RoleDto> result = new ArrayList<>();
        for (Role role : roles){
            result.add(new RoleDto(role.getId(), role.getName()));
        }
        return result;
    }


    @RequestMapping(value="/roles/{roleId}", method = RequestMethod.GET)
    @ApiOperation (value="Retrieve a Role")
    public RoleDto get (@PathVariable long roleId) throws RoleException{
        Role role = roleService.findById(roleId);
        if (role == null){
            throw new RoleNotFoundException("Role not found: " + roleId);
        }
        return new RoleDto(role.getId(), role.getName());
    }


    //-- Exception handlers
    @ResponseStatus (value= HttpStatus.NOT_FOUND)
    @ExceptionHandler (RoleNotFoundException.class)
    public void notFound (){    // NOSONAR - This function is left empty intentionally
    }

}

