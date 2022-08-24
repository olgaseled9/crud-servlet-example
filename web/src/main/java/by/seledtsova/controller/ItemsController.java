package by.seledtsova.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.ItemDto;
import by.seledtsova.service.ItemService;
import by.seledtsova.service.impl.ItemServiceImpl;

import static by.seledtsova.constant.PagesConstant.ITEM_LIST_JSP_PAGE;
import static by.seledtsova.constant.PagesConstant.ITEM_LIST_MODEL;
import static by.seledtsova.constant.PagesConstant.JSP_PAGES_LOCATION;
import static by.seledtsova.controller.ItemsController.GET_ALL_ITEMS_CONTROLLER_URL;

/**
 * Handles the request to get all selected {@link ItemDto}.
 */
@WebServlet(urlPatterns = {GET_ALL_ITEMS_CONTROLLER_URL})
public class ItemsController extends HttpServlet {

    /**
     * Url which handles requests to get all items page.
     */
    public static final String GET_ALL_ITEMS_CONTROLLER_URL = "/items";

    /**
     * Creates {@link ItemService} object to invoke methods on.
     */
    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDto> items = itemService.getAll();
        request.setAttribute(ITEM_LIST_MODEL, items);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_PAGES_LOCATION + ITEM_LIST_JSP_PAGE);
        requestDispatcher.forward(request, response);
    }
}
