package by.seledtsova.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.UserDto;
import by.seledtsova.service.UserService;
import by.seledtsova.service.impl.UserServiceImpl;

import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.constant.PagesConstant.JSP_PAGES_LOCATION;
import static by.seledtsova.constant.PagesConstant.USER_EDIT_JSP_PAGE;
import static by.seledtsova.constant.PagesConstant.USER_MODEL;
import static by.seledtsova.controller.user.UserController.GET_ALL_USERS_CONTROLLER_URL;
import static by.seledtsova.validation.ItemValidation.hasParameter;
import static by.seledtsova.validation.UserValidation.setFieldsToUserFromRequest;

/**
 * Servlet that handles the request to add or update an {@link UserDto}.
 */
@WebServlet(urlPatterns = {EditUserController.EDIT_USER_CONTROLLER_URL})
public class EditUserController extends HttpServlet {

    /**
     * Url which handles user's add or edit requests.
     */
    public static final String EDIT_USER_CONTROLLER_URL = "/users/edit";

    /**
     * Creates {@link UserService} object to invoke methods on.
     */
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userForPage;
        if (hasParameter(request, ID)) {
            Long id = Long.parseLong(request.getParameter(ID));
            userForPage = userService.findById(id);
        }
        else {
            userForPage = new UserDto();
        }
        request.setAttribute(USER_MODEL, userForPage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_PAGES_LOCATION + USER_EDIT_JSP_PAGE);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDto userToDB = setFieldsToUserFromRequest(request);
        if (userToDB.isNew()) {
            userService.add(userToDB);
        }
        else {
            userService.update(userToDB);
        }
        response.sendRedirect(request.getContextPath() + GET_ALL_USERS_CONTROLLER_URL);
    }
}
