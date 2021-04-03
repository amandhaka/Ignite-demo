package com.example.ignite.repository.jpa;

import com.example.ignite.entity.postgresql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface PostgresEmployeeRepository extends JpaRepository<Employee,Long> {
}
