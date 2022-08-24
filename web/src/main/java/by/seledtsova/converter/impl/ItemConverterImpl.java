package by.seledtsova.converter.impl;

import by.seledtsova.converter.ItemConverter;
import by.seledtsova.dto.ItemDto;
import by.seledtsova.model.Item;

/**
 * Is an implementation of the {@link ItemConverter} interface.
 */
public class ItemConverterImpl implements ItemConverter {

    @Override
    public ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        return itemDto;
    }

    @Override
    public Item convertBack(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        return item;
    }
}
