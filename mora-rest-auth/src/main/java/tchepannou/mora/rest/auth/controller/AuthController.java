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
import tchepannou.mora.core.exception.AccessTokenNotFoundException;
import tchepannou.mora.core.exception.AuthException;
import tchepannou.mora.core.service.AuthService;
import tchepannou.mora.rest.auth.dto.AccessTokenDto;
import tchepannou.mora.rest.auth.dto.AuthRequest;

import javax.validation.Valid;

@RestController
@RequestMapping (value = "/auth")
@Api (value="Authentication", description = "Authenticates users")
public class AuthController {
    //-- Attributes
    public static final String SUCCESS = "success";
    public static final String ERROR_AUTH_FAILED = "auth_failed";
    public static final String ERROR_NOT_FOUND = "not_found";
    public static final String ERROR_UNAUTHORIZED = "unauthorized";

    @Autowired
    private AuthService authService;

    //-- REST methods
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation (value="Get the user's access token")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 404, message = ERROR_NOT_FOUND),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
    })
    public AccessTokenDto get(@RequestHeader ("X-AccessToken") String key) throws AuthException {
        AccessToken token = authService.findByValue(key);
        if (token == null){
            throw new AccessTokenNotFoundException("No token found for " + key);
        } if (token.isExpired()){
            throw new AuthException("Token has expired");
        }

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ApiOperation (value="Release the current access token")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS)
    })
    public void delete(@RequestHeader ("X-AccessToken") String key){
        authService.logout(key);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ApiOperation (value="Authenticate a user")
    @ApiResponses ({
            @ApiResponse (code = 200, message = SUCCESS),
            @ApiResponse (code = 401, message = ERROR_UNAUTHORIZED),
    })
    public AccessTokenDto authenticate (@Valid @RequestBody AuthRequest request) throws AuthException{
        AccessToken token = authService.authenticate(request.getUsernameOrEmail(), request.getPassword());

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }


    //-- Error handlers
    @ResponseStatus (value= HttpStatus.NOT_FOUND, reason = ERROR_NOT_FOUND)
    @ExceptionHandler (AccessTokenNotFoundException.class)
    public void notFound(){  // NOSONAR - This function is left empty intentionally
    }

    @ResponseStatus (value= HttpStatus.UNAUTHORIZED, reason = ERROR_UNAUTHORIZED)
    @ExceptionHandler (AuthException.class)
    public void unauthorized(){  // NOSONAR - This function is left empty intentionally
    }
}
