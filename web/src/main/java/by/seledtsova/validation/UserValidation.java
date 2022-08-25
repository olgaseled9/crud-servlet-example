package by.seledtsova.validation;

import javax.servlet.http.HttpServletRequest;

import by.seledtsova.dto.UserDto;
import by.seledtsova.model.RoleValue;

import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.validation.ItemValidation.hasParameter;

/**
 * Item's form validator.
 */
public class UserValidation {

    /**
     * MAX input data length.
     */
    public static final int MAX_LENGTH = 100;

    /**
     * Takes parameters from request and fills the {@link UserDto} object with them.
     * @param request contains data from the page
     */
    public static UserDto setFieldsToUserFromRequest(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        if (hasParameter(request, ID)) {
            userDto.setUserId(Long.valueOf(request.getParameter(ID)));
        }
        userDto.setFirstname(request.getParameter("firstname"));
        userDto.setLastname(request.getParameter("lastname"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setLogin(request.getParameter("login"));
        userDto.setRole(RoleValue.valueOf(request.getParameter("role")));
        return userDto;
    }

    /**
     * Checks is the entered data has errors.
     * @return returns true if the data is invalid
     */
    public static boolean isEmptyOrNull(String value) {
        return value == null || value.isEmpty();
    }


}



