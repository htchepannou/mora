package tchepannou.mora.rest.auth.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.rest.auth.dto.AccessTokenDto;
import tchepannou.mora.rest.auth.dto.AuthRequest;

import javax.validation.Valid;

@RestController
@Api (value="AccessToken", description = "Manages access token")
public class AccessTokenController {
    //-- Attributes
    public static final String HEADER_TOKEN = "X-AccessToken";
    public static final String SUCCESS = "success";
    public static final String ERROR_AUTH_FAILED = "auth_failed";
    public static final String ERROR_UNAUTHORIZED = "unauthorized";
    public static final String ERROR_BAD_REQUEST = "bad_request";

    @Autowired
    private AccessTokenService accessTokenService;

    //-- REST methods
    @RequestMapping(value="/access_token", method = RequestMethod.GET)
    @ApiOperation (value="Get the user's access token")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
    })
    public AccessTokenDto get(@RequestHeader (HEADER_TOKEN)  String key) throws AccessTokenException {
        AccessToken token = accessTokenService.findByValue(key);
        if (token == null){
            throw new AccessTokenException("No token found for " + key);
        } if (token.isExpired()){
            throw new AccessTokenException("Token has expired");
        }

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }

    @RequestMapping(value="/access_token", method = RequestMethod.DELETE)
    @ApiOperation (value="Release the current access token")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
    })
    public void delete(@RequestHeader (HEADER_TOKEN) String key){
        accessTokenService.logout(key);
    }

    @RequestMapping(value="/access_token", method = RequestMethod.PUT)
    @ApiOperation (value="Authenticate a user and assign him a new token")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
            @ApiResponse (code = 409, message = ERROR_AUTH_FAILED),
    })
    public AccessTokenDto create (@Valid @RequestBody AuthRequest request) throws AccessTokenException {
        AccessToken token = accessTokenService.authenticate(request.getUsernameOrEmail(), request.getPassword());

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }


    //-- Error handlers
    @ResponseStatus (value= HttpStatus.CONFLICT, reason = ERROR_AUTH_FAILED)
    @ExceptionHandler (AuthFailedException.class)
    public void authFailed(){  // NOSONAR - This function is left empty intentionally
    }

    @ResponseStatus (value= HttpStatus.UNAUTHORIZED, reason = ERROR_UNAUTHORIZED)
    @ExceptionHandler (AccessTokenException.class)
    public void unauthorized(){  // NOSONAR - This function is left empty intentionally
    }
}
