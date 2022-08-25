package by.seledtsova.controller.user;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.UserDto;
import by.seledtsova.service.UserService;
import by.seledtsova.service.impl.UserServiceImpl;

import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.controller.user.DeleteUserController.DELETE_USER_CONTROLLER_URL;
import static by.seledtsova.controller.user.UserController.GET_ALL_USERS_CONTROLLER_URL;

/**
 * Servlet that handles the request to delete {@link UserDto} object.
 */
@WebServlet(urlPatterns = {DELETE_USER_CONTROLLER_URL})
public class DeleteUserController extends HttpServlet {

    /**
     * Url which handles item's delete requests.
     */
    public static final String DELETE_USER_CONTROLLER_URL = "/users/delete";

    /**
     * Creates {@link UserService} object to invoke methods on.
     */
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter(ID));
        userService.removeById(id);
        response.sendRedirect(request.getContextPath() + GET_ALL_USERS_CONTROLLER_URL);
    }
}
