package by.seledtsova.validation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.seledtsova.dto.ItemDto;

import static by.seledtsova.constant.PagesConstant.DESCRIPTION;
import static by.seledtsova.constant.PagesConstant.ID;
import static by.seledtsova.constant.PagesConstant.NAME;

/**
 * Item's form validator.
 */
public class ItemValidation {

    /**
     * MAX input data length.
     */
    public static final int MAX_LENGTH = 200;

    /**
     * Takes parameters from request and fills the {@link ItemDto} object with them.
     * @param request contains data from the page
     */
    public static ItemDto setFieldsToItemFromRequest(HttpServletRequest request) {
        ItemDto itemDto = new ItemDto();
        if (hasParameter(request, ID)) {
            itemDto.setId(Long.valueOf(request.getParameter(ID)));
        }
        itemDto.setName(request.getParameter(NAME).trim());
        itemDto.setDescription(request.getParameter(DESCRIPTION).trim());
        return itemDto;
    }

    /**
     * Checks is the request has specific parameter.
     * @return returns true if the data is invalid
     */
    public static boolean hasParameter(HttpServletRequest request, String name) {
        return request.getParameter(name) != null && !request.getParameter(name).isEmpty();
    }

    /**
     * Checks is the entered data has errors.
     * @return returns true if the data is invalid
     */
    static boolean isEmptyOrNull(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Set error attributes to item's model
     * @param itemFromPage model from page
     * @return itemFromPage item's model with errors if entered data is invalid
     */
    static ItemDto setErrorMessageAttributeIfInvalidData(ItemDto itemFromPage) {
        List<String> errors = new ArrayList<>();
        if (isEmptyOrNull(itemFromPage.getName())) {
            errors.add("FIELD '" + NAME + "' is required.");
        }
        if (isEmptyOrNull(itemFromPage.getDescription())) {
            errors.add("FIELD '" + DESCRIPTION + "' is required.");
        }
        if (itemFromPage.getName().length() > MAX_LENGTH) {
            errors.add("FIELD '" + NAME + "' must not exceed the length " + MAX_LENGTH + ".");
        }
        if (itemFromPage.getDescription().length() > MAX_LENGTH) {
            errors.add("FIELD '" + DESCRIPTION + "' must not exceed the length " + MAX_LENGTH + ".");
        }
        itemFromPage.setErrors(errors);
        return itemFromPage;
    }

    /**
     * Checks is the entered data has errors.
     * @return returns true if the data is invalid
     */
    public static boolean hasErrors(ItemDto itemDto) {
        setErrorMessageAttributeIfInvalidData(itemDto);
        return !(itemDto.getErrors().isEmpty());
    }
}



