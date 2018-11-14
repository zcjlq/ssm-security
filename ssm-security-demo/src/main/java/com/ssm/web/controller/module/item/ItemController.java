package com.ssm.web.controller.module.item;


import com.ssm.dto.module.item.Item;
import com.ssm.service.module.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 贾令强
 * @since 2018/4/22 23:28
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/add")
    public ResponseEntity<String> addItem(Item item) {
        itemService.addItem(item);
        System.out.println(item);
        // todo 乱码
        String json = "success";
        ResponseEntity<String> re = new ResponseEntity<String>(json, HttpStatus.OK);
        return re;
    }

    @RequestMapping("/list")
    public List<Item> itemList() {
        List<Item> itemList = itemService.getItemList();
        return itemList;
    }

    @RequestMapping("/delete")
    public String delete(String itemIds) {
        itemService.delete(itemIds);
        return "ok";
    }

    @RequestMapping("/names")
    public void getNames() {
        List<Item> itemList = itemService.getNames();

    }
}
