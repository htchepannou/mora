package tchepannou.mora.rest.space.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.exception.BadRequestException;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.space.dto.CreateSpaceDto;
import tchepannou.mora.rest.space.dto.SaveSpaceDto;
import tchepannou.mora.rest.space.dto.SpaceDto;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpaceController extends BaseRestController{
    //-- Attributes
    @Autowired
    private SpaceTypeService spaceTypeService;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;


    //-- REST methods
    @RequestMapping (value = "/spaces/types", method = RequestMethod.GET)
    public List<EnumDto> types (){
        return spaceTypeService.findAll().stream()
                .map(type -> new EnumDto.Builder().withEnum(type).build())
                .collect(Collectors.toList());
    }

    @RequestMapping (value = "/spaces/types/{typeId}", method = RequestMethod.GET)
    public EnumDto type (@PathVariable long typeId) {
        SpaceType type = spaceTypeService.findById(typeId);
        if (type == null){
            throw new NotFoundException(typeId);
        }
        return new EnumDto.Builder().withEnum(type).build();
    }


    @RequestMapping (value = "/spaces/{spaceId}", method = RequestMethod.GET)
    public SpaceDto get (@PathVariable long spaceId){
        Space space = spaceService.findById(spaceId);
        if (space == null){
            throw new NotFoundException(spaceId);
        }

        SpaceType spaceType = spaceTypeService.findById(space.getTypeId());

        return new SpaceDto.Builder()
                .withSpace(space)
                .withSpaceType(spaceType)
                .build();
    }

    @RequestMapping (value = "/spaces", method = RequestMethod.PUT)
    public SpaceDto create (@AuthenticationPrincipal Principal currentToken, @Valid  @RequestBody CreateSpaceDto request){
        User user = getCurrentUser(currentToken);

        SpaceType type = spaceTypeService.findById(request.getTypeId());
        if (type == null){
            throw new BadRequestException("typeId");
        }

        Space space = new Space(type, user);

        request.toSpace(space);

        spaceService.create(space);

        return new SpaceDto.Builder()
                .withSpace(space)
                .withSpaceType(type)
                .build();
    }

    @RequestMapping (value = "/spaces/{spaceId}", method = RequestMethod.POST)
    public SpaceDto update (@PathVariable long spaceId, @Valid  @RequestBody SaveSpaceDto request) {
        Space space = spaceService.findById(spaceId);
        if (space == null){
            throw new NotFoundException(spaceId);
        }

        request.toSpace(space);

        spaceService.update(space);

        SpaceType type = spaceTypeService.findById(space.getTypeId());
        return new SpaceDto.Builder()
                .withSpace(space)
                .withSpaceType(type)
                .build();
    }

    @RequestMapping (value = "/spaces/{spaceId}", method = RequestMethod.DELETE)
    public void delete (@PathVariable long spaceId) {
        Space space = spaceService.findById(spaceId);
        if (space != null){
            spaceService.delete(space);
        }
    }


    //-- Private
    protected User getCurrentUser (Principal currentToken) {
        String token = currentToken.getName();
        AccessToken accessToken = accessTokenService.findByValue(token);
        return userService.findById(accessToken.getUserId());
    }
}
