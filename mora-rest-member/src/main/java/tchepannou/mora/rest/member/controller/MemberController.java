package tchepannou.mora.rest.member.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.exception.MemberException;
import tchepannou.mora.core.exception.MemberNotFoundException;
import tchepannou.mora.core.exception.RoleException;
import tchepannou.mora.core.exception.RoleNotFoundException;
import tchepannou.mora.core.exception.SpaceException;
import tchepannou.mora.core.exception.SpaceNotFoundException;
import tchepannou.mora.core.exception.UserException;
import tchepannou.mora.core.exception.UserNotFoundException;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.RoleService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.member.dto.MemberDto;


@RestController
@Api (value="Members", description = "Manage members")
public class MemberController {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private SpaceService spaceService;
    
    @Autowired
    private SpaceTypeService spaceTypeService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    
    //-- Public
    @ApiOperation("Add new Member")
    @RequestMapping(value="/spaces/{spaceId}/users/{userId}/roles/{roleId}", method= RequestMethod.PUT)
    public MemberDto create (long spaceId, long userId, long roleId) throws SpaceException, UserException, RoleException, MemberException{
        Space space = spaceService.findById(spaceId);
        if (space == null){
            throw new SpaceNotFoundException(String.valueOf(spaceId));
        }

        User user = userService.findById (userId);
        if (user == null){
            throw new UserNotFoundException(String.valueOf(userId));
        }

        Role role = roleService.findById(roleId);
        if (role == null){
            throw new RoleNotFoundException(String.valueOf(roleId));
        }

        Member member = new Member(0, space, user, role);
        try {
            memberService.create(member);

        } catch (MemberDuplicationException e){
            LOG.warn(user + " + is already member of " + space + " as " + role, e);

            member = memberService.findBySpaceByUserByRole(spaceId, userId, roleId);
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }
    
    @ApiOperation("Update a Member")
    @RequestMapping(value="/spaces/{spaceId}/users/{userId}/roles/{roleId}", method= RequestMethod.POST)
    public MemberDto update (long spaceId, long userId, long roleId, @RequestParam("role") long newRoleId) throws MemberException, RoleException{
        Role role = roleService.findById(newRoleId);
        if (role == null){
            throw new RoleNotFoundException(String.valueOf(newRoleId));
        }

        Member member = memberService.findBySpaceByUserByRole(spaceId, userId, roleId);
        member.setRoleId(newRoleId);
        try {
            memberService.create(member);
        } catch (MemberDuplicationException e){
            LOG.warn(member + " already exists", e);
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }
    
    @ApiOperation("Delete a Member")
    @RequestMapping(value="/spaces/{spaceId}/users/{userId}/roles/{roleId}", method= RequestMethod.DELETE)
    public void delete(long spaceId, long userId, long roleId) throws MemberException{
        Member member = memberService.findBySpaceByUserByRole(spaceId, userId, roleId);
        if (member != null){
            memberService.delete(member);
        }
    }


    //-- Error handlers
    @ResponseStatus (value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler ({UserNotFoundException.class, SpaceNotFoundException.class, RoleNotFoundException.class})
    public void badRequest (){    // NOSONAR - This function is left empty intentionally
    }

    @ResponseStatus (value= HttpStatus.NOT_FOUND)
    @ExceptionHandler (MemberNotFoundException.class)
    public void notFound (){    // NOSONAR - This function is left empty intentionally
    }
}
