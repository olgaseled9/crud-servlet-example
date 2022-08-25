package by.seledtsova.converter;

import by.seledtsova.dto.UserDto;
import by.seledtsova.model.User;

/**
 * Converts a container of User entity data {@link User}
 * into a container for representing data {@link UserDto}.
 */
public interface UserConverter extends GenericConverter<UserDto, User> {

}

