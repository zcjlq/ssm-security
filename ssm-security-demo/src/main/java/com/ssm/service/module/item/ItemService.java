package com.ssm.service.module.item;


import com.ssm.dto.module.item.Item;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/22 23:29
 */
public interface ItemService {

    void addItem(Item item);

    List<Item> getItemList();

    void delete(String itemIds);

    List<Item> getNames();
}
