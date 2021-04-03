package com.example.ignite.service;

import com.example.ignite.entity.Item;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IgniteItemService {
   
    Mono<Item> saveBulkItemToCache(MultipartFile file);

    List<Item> viewItemList();

}
