package tchepannou.mora.rest.security.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.EmailAlreadyAssignedException;
import tchepannou.mora.core.exception.UserException;
import tchepannou.mora.core.exception.UsernameAlreadyAssignedException;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.core.exception.OperationFailedException;
import tchepannou.mora.rest.security.dto.CreateUserDto;
import tchepannou.mora.rest.security.dto.SaveUserDto;
import tchepannou.mora.rest.security.dto.UserDto;

import javax.validation.Valid;

@RestController
@Api (value="Users", description = "Manage users")
public class UserController extends BaseRestController{
    //-- Attributes
    public static final String ERROR_USERNAME_ALREADY_ASSIGNED = "username_already_assigned";
    public static final String ERROR_EMAIL_ALREADY_ASSIGNED = "email_already_assigned";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;


    //-- REST methods
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    @ApiOperation(value="Retrieve User")
    public UserDto get(@PathVariable long userId) {
        User user = userService.findById(userId);
        if (user == null){
            throw new NotFoundException(userId);
        }

        return new UserDto.Builder()
                .withUser(user)
                .build();
    }

    @RequestMapping(value="/users", method = RequestMethod.PUT)
    @ApiOperation(value="Create New User")
    public UserDto create (@Valid @RequestBody CreateUserDto request) throws UserException{
        try {
            /* Create the user */
            User user = new User();
            request.toUser(user);
            userService.create(user);

            /* create the password */
            Password password = new Password(user);
            password.setValue(passwordService.encrypt(request.getPassword()));
            passwordService.create(password);

            return new UserDto.Builder().withUser(user).build();
        } catch (UsernameAlreadyAssignedException e){
            throw new OperationFailedException(ERROR_USERNAME_ALREADY_ASSIGNED, e);
        } catch (EmailAlreadyAssignedException e){
            throw new OperationFailedException(ERROR_EMAIL_ALREADY_ASSIGNED, e);
        }
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.POST)
    @ApiOperation(value="Update User")
    public UserDto update (@PathVariable long userId, @Valid @RequestBody SaveUserDto request) throws UserException {
        try{
            User user = userService.findById(userId);
            if (user == null) {
                throw new NotFoundException("User not found: " + userId);
            }

            request.toUser(user);
            userService.update(user);

            return new UserDto.Builder()
                    .withUser(user)
                    .build();
        } catch (UsernameAlreadyAssignedException e){
            throw new OperationFailedException(ERROR_USERNAME_ALREADY_ASSIGNED, e);
        } catch (EmailAlreadyAssignedException e){
            throw new OperationFailedException(ERROR_EMAIL_ALREADY_ASSIGNED, e);
        }
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.DELETE)
    @ApiOperation(value="Delete User")
    public void delete (@PathVariable long userId){
        User user = userService.findById(userId);
        if (user != null) {
            userService.delete(user);
        }
    }
}
