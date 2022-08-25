package by.seledtsova.converter.impl;

import by.seledtsova.converter.UserConverter;
import by.seledtsova.dao.RoleDao;
import by.seledtsova.dao.impl.RoleDaoImpl;
import by.seledtsova.dto.UserDto;
import by.seledtsova.model.Role;
import by.seledtsova.model.User;

/**
 * Is an implementation of the {@link UserConverter} interface.
 */
public class UserConverterImpl implements UserConverter {

    /**
     * Creates {@link RoleDao} object to invoke methods on.
     */
    private final RoleDao roleDao = new RoleDaoImpl();

    @Override
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }

    @Override
    public User convertBack(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        Role role = roleDao.findByName(userDto.getRole());
        user.setRole(role);
        return user;
    }
}
