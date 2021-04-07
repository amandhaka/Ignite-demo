package com.example.ignite.service.impl;

import com.example.ignite.entity.Item;
import com.example.ignite.repository.ignite.IgniteItemRepository;
import com.example.ignite.service.IgniteItemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class IgniteItemServiceImpl implements IgniteItemService {

    @Autowired
    IgniteItemRepository repo;

    @Override
    public Mono<Item> saveBulkItemToCache(MultipartFile file) {
        return Mono.create(consumer -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                InputStream in = file.getInputStream();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                List<Item> itemList = objectMapper.readValue( in, new TypeReference<List<Item>>(){});
                itemList.forEach(e->repo.save(String.valueOf(Math.random()),e));
            } catch (Exception ex ) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public List<Item> viewItemList() {
        List<Item> itemList = new ArrayList<>();
        System.out.println(repo.getAllItems());
        repo.findAll().forEach(e->itemList.add(e));
        return itemList;
    }

    @Override
    public String deleteAllItem() {
        repo.deleteAll();
        return "Deleted Successfully";
    }

}
