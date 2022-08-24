package by.seledtsova.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.ItemDto;
import by.seledtsova.service.ItemService;
import by.seledtsova.service.impl.ItemServiceImpl;

import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.constant.PagesConstant.ITEM_EDIT_JSP_PAGE;
import static by.seledtsova.constant.PagesConstant.ITEM_MODEL;
import static by.seledtsova.constant.PagesConstant.JSP_PAGES_LOCATION;
import static by.seledtsova.controller.EditItemController.EDIT_CONTROLLER_URL;
import static by.seledtsova.controller.ItemsController.GET_ALL_ITEMS_CONTROLLER_URL;
import static by.seledtsova.validation.ItemValidation.hasErrors;
import static by.seledtsova.validation.ItemValidation.hasParameter;
import static by.seledtsova.validation.ItemValidation.setFieldsToItemFromRequest;

/**
 * Servlet that handles the request to add or update an {@link ItemDto}.
 */
@WebServlet(urlPatterns = {EDIT_CONTROLLER_URL})
public class EditItemController extends HttpServlet {

    /**
     * Url which handles item's add or edit requests.
     */
    public static final String EDIT_CONTROLLER_URL = "/items/edit";

    /**
     * Creates {@link ItemService} object to invoke methods on.
     */
    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDto itemForPage;
        if (hasParameter(request, ID)) {
            Long id = Long.parseLong(request.getParameter(ID));
            itemForPage = itemService.findById(id);
        }
        else {
            itemForPage = new ItemDto();
        }
        request.setAttribute(ITEM_MODEL, itemForPage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_PAGES_LOCATION + ITEM_EDIT_JSP_PAGE);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ItemDto itemToDB = setFieldsToItemFromRequest(request);
        if (hasErrors(itemToDB)) {
            request.setAttribute(ITEM_MODEL, itemToDB);
            request.getRequestDispatcher(JSP_PAGES_LOCATION + ITEM_EDIT_JSP_PAGE).forward(request, response);
            return;
        }
        if (itemToDB.isNew()) {
            itemService.add(itemToDB);
        }
        else {
            itemService.update(itemToDB);
        }
        response.sendRedirect(request.getContextPath() + GET_ALL_ITEMS_CONTROLLER_URL);
    }
}
