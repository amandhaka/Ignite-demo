package com.example.ignite.repository.ignite;

import com.example.ignite.entity.Employee;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryConfig(cacheName = "employee")
public interface IgniteEmployeeRepository extends IgniteRepository<Employee, Long> {
}
