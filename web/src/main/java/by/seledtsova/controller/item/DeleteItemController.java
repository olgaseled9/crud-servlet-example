package by.seledtsova.controller.item;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.seledtsova.dto.ItemDto;
import by.seledtsova.service.ItemService;
import by.seledtsova.service.impl.ItemServiceImpl;

import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.controller.item.DeleteItemController.DELETE_ITEM_CONTROLLER_URL;
import static by.seledtsova.controller.item.ItemsController.GET_ALL_ITEMS_CONTROLLER_URL;

/**
 * Servlet that handles the request to delete {@link ItemDto} object.
 */
@WebServlet(urlPatterns = {DELETE_ITEM_CONTROLLER_URL})
public class DeleteItemController extends HttpServlet {

    /**
     * Url which handles item's delete requests.
     */
    public static final String DELETE_ITEM_CONTROLLER_URL = "/items/delete";

    /**
     * Creates {@link ItemService} object to invoke methods on.
     */
    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter(ID));
        itemService.removeById(id);
        response.sendRedirect(request.getContextPath() + GET_ALL_ITEMS_CONTROLLER_URL);
    }
}
