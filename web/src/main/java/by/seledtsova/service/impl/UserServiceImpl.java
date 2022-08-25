package by.seledtsova.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.seledtsova.converter.UserConverter;
import by.seledtsova.converter.impl.UserConverterImpl;
import by.seledtsova.dao.UserDao;
import by.seledtsova.dao.impl.UserDaoImpl;
import by.seledtsova.dto.UserDto;
import by.seledtsova.exception.DaoException;
import by.seledtsova.exception.ServiceException;
import by.seledtsova.model.User;
import by.seledtsova.service.UserService;

/**
 * Is an implementation of the {@link UserService} interface.
 */
public class UserServiceImpl implements UserService {

    /**
     * Converter between {@link UserDto} and {@link User}.
     */
    private final UserConverter converter = new UserConverterImpl();

    /**
     * Creates userDao object {@link UserDao} to handle the database operation.
     */
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserDto findById(Long id) {
        UserDto userDto;
        try {
            User user = userDao.findById(id);
            userDto = converter.convertToDto(user);
            return userDto;
        }
        catch (DaoException e) {
            throw new ServiceException("User is not found with id = " + id, e);
        }
    }

    @Override
    public void add(UserDto userDto) {
        try {
            User user = converter.convertBack(userDto);
            userDao.add(user);
            userDto.setUserId(user.getUserId());
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to add new user : " + userDto, e);
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            userDao.removeById(id);
        }
        catch (DaoException e) {
            throw new ServiceException("Cannot be removed user with id = " + id, e);
        }
    }

    @Override
    public void update(UserDto userDto) {
        try {
            User user = converter.convertBack(userDto);
            userDao.update(user);
        }
        catch (DaoException e) {
            throw new ServiceException("User is not updated : " + userDto, e);
        }
    }

    @Override
    public List<UserDto> getAll() {
        try {
            List<User> userList = userDao.getAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : userList) {
                userDtoList.add(converter.convertToDto(user));
            }
            return userDtoList;
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to get all users. ", e);
        }
    }
}
