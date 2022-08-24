package by.seledtsova.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.seledtsova.converter.ItemConverter;
import by.seledtsova.converter.impl.ItemConverterImpl;
import by.seledtsova.dao.ItemDao;
import by.seledtsova.dao.impl.ItemDaoImpl;
import by.seledtsova.dto.ItemDto;
import by.seledtsova.exception.DaoException;
import by.seledtsova.exception.ServiceException;
import by.seledtsova.model.Item;
import by.seledtsova.service.ItemService;

/**
 * Is an implementation of the {@link ItemService} interface.
 */
public class ItemServiceImpl implements ItemService {

    /**
     * Converter between {@link ItemDto} and {@link Item}.
     */
    private final ItemConverter converter = new ItemConverterImpl();

    /**
     * Creates ItemDao object {@link ItemDao} to handle the database operation.
     */
    private final ItemDao itemDao = new ItemDaoImpl();


    @Override
    public ItemDto findById(Long id) {
        ItemDto itemDto;
        try {
            Item item = itemDao.findById(id);
            itemDto = converter.convertToDto(item);
            return itemDto;
        }
        catch (DaoException e) {
            throw new ServiceException("Item is not found with id = " + id, e);
        }
    }

    @Override
    public void add(ItemDto itemDto) {
        try {
            Item item = converter.convertBack(itemDto);
            itemDao.add(item);
            itemDto.setId(item.getId());
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to add new item : " + itemDto, e);
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            itemDao.removeById(id);
        }
        catch (DaoException e) {
            throw new ServiceException("Cannot be removed item with id = " + id, e);
        }
    }

    @Override
    public void update(ItemDto itemDto) {
        try {
            Item item = converter.convertBack(itemDto);
            itemDao.update(item);
        }
        catch (DaoException e) {
            throw new ServiceException("Item is not updated : " + itemDto, e);
        }
    }

    @Override
    public List<ItemDto> getAll() {
        try {
            List<Item> itemList = itemDao.getAll();
            List<ItemDto> itemDtoList = new ArrayList<>();
            for (Item item : itemList) {
                itemDtoList.add(converter.convertToDto(item));
            }
            return itemDtoList;
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to get all items. ", e);
        }
    }
}
