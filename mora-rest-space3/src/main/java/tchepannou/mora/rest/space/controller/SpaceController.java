package tchepannou.mora.rest.space.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.SpaceException;
import tchepannou.mora.core.exception.SpaceNotFoundException;
import tchepannou.mora.core.exception.SpaceTypeNotFoundException;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.space.dto.CreateSpaceDto;
import tchepannou.mora.rest.space.dto.SaveSpaceDto;
import tchepannou.mora.rest.space.dto.SpaceDto;
import tchepannou.mora.rest.space.dto.SpaceTypeDto;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api (value="Spaces", description = "Manage the spaces")
public class SpaceController {
    //-- Attributes
    public static final String SUCCESS = "success";
    public static final String ERROR_BAD_REQUEST = "bad_request";
    public static final String ERROR_NOT_FOUND = "space_not_found";

    @Autowired
    private SpaceTypeService spaceTypeService;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private UserService userService;


    //-- REST methods
    @RequestMapping (value = "/spaces/types", method = RequestMethod.GET)
    @ApiOperation (value="Retrieve Type of Spaces")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
    })
    public List<SpaceTypeDto> types (){
        List<SpaceType> types = spaceTypeService.findAll();

        List<SpaceTypeDto> result = new ArrayList<SpaceTypeDto>();
        for (SpaceType type : types){
            SpaceTypeDto dto = new SpaceTypeDto.Builder().withSpaceType(type).build();
            result.add(dto);
        }
        return result;
    }


    @RequestMapping (value = "/spaces", method = RequestMethod.PUT)
    @ApiOperation (value="Create New Spaces")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
    })
    public SpaceDto create (@AuthenticationPrincipal Principal currentUser, @Valid  @RequestBody CreateSpaceDto request) throws SpaceException{
        User user = getCurrentUser(currentUser);

        SpaceType type = spaceTypeService.findById(request.getTypeId());
        if (type == null){
            throw new SpaceTypeNotFoundException("Invalid SpaceType: " + request.getTypeId());
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
    @ApiOperation (value="Update Space")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
            @ApiResponse (code = 404, message = ERROR_NOT_FOUND),
    })
    public SpaceDto update (@PathVariable long spaceId, @Valid  @RequestBody SaveSpaceDto request) throws SpaceException{
        Space space = spaceService.findById(spaceId);
        if (space == null){
            throw new SpaceNotFoundException("Space not found: " + spaceId);
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
    @ApiOperation (value="Delete a Space")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
    })
    public void delete (@PathVariable long spaceId) {
        Space space = spaceService.findById(spaceId);
        if (space != null){
            spaceService.delete(space);
        }
    }


    //-- Private
    protected User getCurrentUser (Principal currentUser) {
        long userId = Long.parseLong(currentUser.getName());
        return userService.findById(userId);
    }


    //-- Exception handlers
    @ResponseStatus (value= HttpStatus.NOT_FOUND)
    @ExceptionHandler (SpaceNotFoundException.class)
    public void notFound (){    // NOSONAR - This function is left empty intentionally
    }

    @ResponseStatus (value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler (SpaceTypeNotFoundException.class)
    public void invalidSpaceType (){    // NOSONAR - This function is left empty intentionally
    }
}
