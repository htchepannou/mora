package tchepannou.mora.rest.security.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.rest.security.dto.AccessTokenDto;
import tchepannou.mora.rest.security.dto.AuthRequest;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@Api (value="AccessToken", description = "Manages access token")
public class AccessTokenController {
    //-- Attributes
    public static final String SUCCESS = "success";
    public static final String ERROR_AUTH_FAILED = "auth_failed";
    public static final String ERROR_UNAUTHORIZED = "unauthorized";
    public static final String ERROR_BAD_REQUEST = "bad_request";

    @Autowired
    private AccessTokenService accessTokenService;

    //-- REST methods
    @RequestMapping(value="/access_token", method = RequestMethod.GET)
    @ApiOperation (value="Retrieve an AccessToken")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
    })
    public AccessTokenDto get(@AuthenticationPrincipal Principal currentToken) throws AccessTokenException {
        AccessToken token = getCurrentAccessToken(currentToken);

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }

    @RequestMapping(value="/access_token", method = RequestMethod.DELETE)
    @ApiOperation (value="Release an AccessToken")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
            @ApiResponse (code = 400, message = ERROR_BAD_REQUEST),
    })
    public void delete(@AuthenticationPrincipal Principal currentToken) throws AccessTokenException {
        AccessToken token = getCurrentAccessToken(currentToken);
        accessTokenService.expire(token);
    }

    @RequestMapping(value="/access_token", method = RequestMethod.PUT)
    @ApiOperation (value="Create New AccessToken")
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


    //-- Private
    private AccessToken getCurrentAccessToken (Principal token) {
        return accessTokenService.findByValue(token.getName());
    }
}
