package tchepannou.mora.rest.space.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.service.SpaceTypeService;
import tchepannou.mora.rest.space.dto.SpaceTypeDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api (value="Spaces", description = "Manage the spaces")
public class SpaceController {
    //-- Attributes
    public static final String SUCCESS = "success";

    @Autowired
    private SpaceTypeService spaceTypeService;


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

}
