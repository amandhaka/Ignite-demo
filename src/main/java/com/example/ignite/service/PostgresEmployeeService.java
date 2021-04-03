package com.example.ignite.service;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.postgresql.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PostgresEmployeeService {
    Mono<Employee> saveEmployeeToPostgresDb(EmployeeRequestDto employeeRequestDto);

    List<Employee> viewEmployeeFromPostgresDb();

    Mono<Void> deleteEmployeeFromPostgresDb();
}
