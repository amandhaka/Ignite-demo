package com.example.ignite.repository.ignite;

import com.example.ignite.entity.Item;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RepositoryConfig(cacheName = "itemCache")
public interface IgniteItemRepository extends IgniteRepository<Item,String> {
}
