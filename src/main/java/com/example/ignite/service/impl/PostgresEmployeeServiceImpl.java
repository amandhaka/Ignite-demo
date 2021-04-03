package com.example.ignite.service.impl;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.postgresql.Employee;
import com.example.ignite.repository.jpa.PostgresEmployeeRepository;
import com.example.ignite.service.PostgresEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PostgresEmployeeServiceImpl implements PostgresEmployeeService {

    @Autowired
    PostgresEmployeeRepository repo;

    @Override
    public Mono<Employee> saveEmployeeToPostgresDb(EmployeeRequestDto employeeRequestDto) {
        return Mono.create(consumer-> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeRequestDto, employee);
            repo.save(employee);
        });
    }

    @Override
    public List<Employee> viewEmployeeFromPostgresDb() {
        return repo.findAll();
    }
    @Override
    public Mono<Void> deleteEmployeeFromPostgresDb() {
        return Mono.create(consumer-> {
            repo.deleteAll();
        });
    }
}
