package com.ssm.service.module.impl.item;


import com.ssm.dto.module.item.Item;
import com.ssm.mapper.module.item.ItemMapper;
import com.ssm.service.module.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/22 23:30
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public void addItem(Item item) {
        itemMapper.addItem(item);
    }

    @Override
    public List<Item> getItemList() {
        return itemMapper.getItemList();
    }

    @Override
    public void delete(String itemIds) {
        List<String> itemIdList = Arrays.asList(itemIds.split(","));
        if (!CollectionUtils.isEmpty(itemIdList)) {
            itemMapper.delete(itemIdList);
        }
    }

    @Override
    public List<Item> getNames() {
        return itemMapper.getNames();
    }
}
