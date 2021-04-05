package com.example.ignite.service;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IgniteEmployeeService {

    Mono<Employee> saveEmployeeToCache(EmployeeRequestDto employeeRequestDto);

    List<Employee> viewEmployeeFromCache();

    Employee viewEmployeeById(Long id);

     void deleteEmployeeFromCache();
}
