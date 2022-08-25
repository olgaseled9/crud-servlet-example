package by.seledtsova.converter;

import by.seledtsova.dto.ItemDto;
import by.seledtsova.model.Item;

/**
 * Converts a container of Item entity data {@link Item}
 * into a container for representing data {@link ItemDto}.
 */
public interface ItemConverter extends GenericConverter<ItemDto, Item> {

}

