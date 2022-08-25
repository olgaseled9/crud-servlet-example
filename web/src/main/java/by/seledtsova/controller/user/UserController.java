package by.seledtsova.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.UserDto;
import by.seledtsova.service.UserService;
import by.seledtsova.service.impl.UserServiceImpl;

import static by.seledtsova.constant.PagesConstant.JSP_PAGES_LOCATION;
import static by.seledtsova.constant.PagesConstant.USER_LIST_JSP_PAGE;
import static by.seledtsova.controller.user.UserController.GET_ALL_USERS_CONTROLLER_URL;

/**
 * Handles the request to get all selected {@link UserDto}.
 */
@WebServlet(urlPatterns = {GET_ALL_USERS_CONTROLLER_URL})
public class UserController extends HttpServlet {

    /**
     * Url which handles requests to get all items page.
     */
    public static final String GET_ALL_USERS_CONTROLLER_URL = "/users";

    /**
     * Creates {@link UserService} object to invoke methods on.
     */
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = userService.getAll();
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_PAGES_LOCATION + USER_LIST_JSP_PAGE);
        requestDispatcher.forward(request, response);
    }
}
