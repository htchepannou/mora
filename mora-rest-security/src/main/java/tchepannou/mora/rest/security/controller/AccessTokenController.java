package tchepannou.mora.rest.security.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.exception.AccessTokenException;
import tchepannou.mora.core.exception.AuthFailedException;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.OperationFailedException;
import tchepannou.mora.rest.security.dto.AccessTokenDto;
import tchepannou.mora.rest.security.dto.AuthRequest;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@Api (value="AccessToken", description = "Manages access token")
public class AccessTokenController extends BaseRestController{
    //-- Attributes
    public static final String ERROR_AUTH_FAILED = "auth_failed";

    @Autowired
    private AccessTokenService accessTokenService;

    //-- REST methods
    @RequestMapping(value="/access_token", method = RequestMethod.GET)
    @ApiOperation (value="Retrieve an AccessToken")
    public AccessTokenDto get(@AuthenticationPrincipal Principal currentToken) {
        AccessToken token = getCurrentAccessToken(currentToken);

        return new AccessTokenDto.Builder()
                .withAccessToken(token)
                .build();
    }

    @RequestMapping(value="/access_token", method = RequestMethod.PUT)
    @ApiOperation (value="Create New AccessToken")
    public AccessTokenDto create (@Valid @RequestBody AuthRequest request) throws AccessTokenException{
        try {
            AccessToken token = accessTokenService.authenticate(request.getUsernameOrEmail(), request.getPassword());

            return new AccessTokenDto.Builder().withAccessToken(token).build();
        } catch (AuthFailedException e){
            throw new OperationFailedException(ERROR_AUTH_FAILED, e);
        }
    }

    @RequestMapping(value="/access_token", method = RequestMethod.DELETE)
    @ApiOperation (value="Release an AccessToken")
    public void delete(@AuthenticationPrincipal Principal currentToken) {
        AccessToken token = getCurrentAccessToken(currentToken);
        accessTokenService.expire(token);
    }

    //-- Private
    private AccessToken getCurrentAccessToken (Principal token) {
        return accessTokenService.findByValue(token.getName());
    }
}
