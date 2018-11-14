package com.ssm.mapper.module.item;


import com.ssm.dto.module.item.Item;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/22 23:31
 */
public interface ItemMapper {

    void addItem(Item item);

    List<Item> getItemList();

    Boolean delete(List<String> itemIds);

    List<Item> getNames();
}
