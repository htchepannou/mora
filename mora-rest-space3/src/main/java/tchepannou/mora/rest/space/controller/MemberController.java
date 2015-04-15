package tchepannou.mora.rest.space.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.DeleteSpaceOwnerException;
import tchepannou.mora.core.exception.MemberDuplicationException;
import tchepannou.mora.core.exception.MemberException;
import tchepannou.mora.core.service.MemberService;
import tchepannou.mora.core.service.RoleService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.BadRequestException;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.core.exception.OperationFailedException;
import tchepannou.mora.rest.space.dto.MemberDto;


@RestController
@Api (value="Members", description = "Manage members")
public class MemberController extends BaseRestController{
    //-- Attributes
    public static final String ERROR_DELETE_SPACE_OWNER = "cant_delete_space_owner";
    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private SpaceService spaceService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    
    //-- Public
    @ApiOperation("Add new Member")
    @RequestMapping(value="/spaces/{spaceId}/users/{userId}/roles/{roleId}", method= RequestMethod.PUT)
    public MemberDto create (long spaceId, long userId, long roleId) throws MemberException{
        Space space = spaceService.findById(spaceId);
        if (space == null){
            throw new NotFoundException(spaceId);
        }

        User user = userService.findById (userId);
        if (user == null){
            throw new NotFoundException(userId);
        }

        Role role = roleService.findById(roleId);
        if (role == null){
            throw new NotFoundException(roleId);
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
    public MemberDto update (long spaceId, long userId, long roleId, @RequestParam("role") long newRoleId) throws MemberException{
        Role role = roleService.findById(newRoleId);
        if (role == null){
            throw new BadRequestException("role");
        }

        Member member = memberService.findBySpaceByUserByRole(spaceId, userId, roleId);
        member.setRoleId(newRoleId);
        try {
            memberService.create(member);
        } catch (MemberDuplicationException e){
            LOG.warn(member + " already exists", e);

            member = memberService.findBySpaceByUserByRole(spaceId, userId, newRoleId);
        }

        return new MemberDto.Builder()
                .withMember(member)
                .build();
    }
    
    @ApiOperation("Delete a Member")
    @RequestMapping(value="/spaces/{spaceId}/users/{userId}/roles/{roleId}", method= RequestMethod.DELETE)
    public void delete(long spaceId, long userId, long roleId) throws MemberException {
        Member member = memberService.findBySpaceByUserByRole(spaceId, userId, roleId);
        if (member != null) {
            try {
                memberService.delete(member);
            } catch (DeleteSpaceOwnerException e) {
                throw new OperationFailedException(ERROR_DELETE_SPACE_OWNER, e);
            }
        }
    }
}
