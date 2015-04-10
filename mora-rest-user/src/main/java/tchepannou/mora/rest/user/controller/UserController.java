package tchepannou.mora.rest.user.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.exception.EmailAlreadyAssignedException;
import tchepannou.mora.core.exception.UserException;
import tchepannou.mora.core.exception.UserNotFoundException;
import tchepannou.mora.core.exception.UsernameAlreadyAssignedException;
import tchepannou.mora.core.service.PasswordService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.user.dto.CreateUserDto;
import tchepannou.mora.rest.user.dto.SaveUserDto;
import tchepannou.mora.rest.user.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@Api (value="Users", description = "Manage users")
public class UserController {
    //-- Attributes
    public static final String ERROR_SUCCESS = "success";
    public static final String ERROR_NOT_FOUND = "user_not_found";
    public static final String ERROR_EMAIL_ALREADY_ASSIGNED = "email_already_assigned";
    public static final String ERROR_USERNAME_ALREADY_ASSIGNED = "username_already_assigned";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;


    //-- REST methods
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value="Get User")
    @ApiResponses({
            @ApiResponse (code = 200, message = ERROR_SUCCESS),
            @ApiResponse (code = 404, message = ERROR_NOT_FOUND),
    })
    public UserDto get(@PathVariable long userId) throws UserNotFoundException {
        User user = userService.findById(userId);
        if (user == null){
            throw new UserNotFoundException(userId);
        }

        return new UserDto.Builder()
                .withUser(user)
                .build();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ApiOperation(value="Create New User")
    @ApiResponses({
            @ApiResponse (code = 200, message = ERROR_SUCCESS),
            @ApiResponse (code = 409, message = ERROR_EMAIL_ALREADY_ASSIGNED),
            @ApiResponse (code = 409, message = ERROR_USERNAME_ALREADY_ASSIGNED),
    })
    public UserDto create (@Valid @RequestBody CreateUserDto request) throws UserException{
        /* Create the user */
        User user = new User ();
        request.toUser(user);
        userService.create(user);

        /* create the password */
        Password password        = new Password(user);
        password.setValue(passwordService.encrypt(request.getPassword()));
        passwordService.create(password);

        return new UserDto.Builder()
                .withUser(user)
                .build();
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.POST)
    @ApiOperation(value="Update User")
    @ApiResponses({
            @ApiResponse (code = 200, message = ERROR_SUCCESS),
            @ApiResponse (code = 404, message = ERROR_NOT_FOUND),
            @ApiResponse (code = 409, message = ERROR_EMAIL_ALREADY_ASSIGNED),
            @ApiResponse (code = 409, message = ERROR_USERNAME_ALREADY_ASSIGNED),
    })
    public UserDto update (@PathVariable long userId, @Valid @RequestBody SaveUserDto request) throws UserException {
        User user = userService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        request.toUser(user);
        userService.update(user);

        return new UserDto.Builder()
                .withUser(user)
                .build();
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.DELETE)
    @ApiOperation(value="Delete User")
    @ApiResponses({
            @ApiResponse (code = 200, message = ERROR_SUCCESS),
    })
    public void delete (@PathVariable long userId){
        User user = userService.findById(userId);
        if (user != null) {
            userService.delete(user);
        }
    }

    //-- Exception handlers
    @ResponseStatus (value= HttpStatus.NOT_FOUND)
    @ExceptionHandler (UserNotFoundException.class)
    public void notFound (){    // NOSONAR - This function is left empty intentionally
    }

    @ResponseStatus (value= HttpStatus.CONFLICT, reason = ERROR_EMAIL_ALREADY_ASSIGNED)
    @ExceptionHandler (EmailAlreadyAssignedException.class)
    public void emailAlreadyAssigned(){ // NOSONAR - This function is left empty intentionally

    }

    @ResponseStatus (value= HttpStatus.CONFLICT, reason = ERROR_USERNAME_ALREADY_ASSIGNED)
    @ExceptionHandler (UsernameAlreadyAssignedException.class)
    public void usernameAlreadyAssigned(){  // NOSONAR - This function is left empty intentionally

    }
}
