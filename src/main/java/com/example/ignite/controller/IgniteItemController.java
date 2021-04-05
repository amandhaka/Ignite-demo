package com.example.ignite.controller;

import com.example.ignite.entity.Item;
import com.example.ignite.service.IgniteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping(path = "/ignite/item")
public class IgniteItemController {

    @Autowired
    IgniteItemService igniteItemService;

    @PostMapping(value = "/bulk",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public String addItemToDatabase(@RequestParam("file") MultipartFile file) {
        igniteItemService.saveBulkItemToCache(file)
                .subscribeOn(Schedulers.parallel())
                .log()
                .subscribe();
        return "Uploading";
    }

    @GetMapping(value="/getItemList")
    public List<Item> viewItemList() {
        return igniteItemService.viewItemList();
    }

    @DeleteMapping("/deleteItem")
    public String deleteAllItem(){
        return igniteItemService.deleteAllItem();
    }

}
