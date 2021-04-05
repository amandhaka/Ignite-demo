package com.example.ignite.service.impl;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.Employee;
import com.example.ignite.repository.ignite.IgniteEmployeeRepository;
import com.example.ignite.service.IgniteEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class IgniteEmployeeServiceImpl implements IgniteEmployeeService {

    @Autowired
    IgniteEmployeeRepository repo;

    @Override
    public List<Employee> viewEmployeeFromCache() {
        List<Employee> listOfEmployee = new ArrayList<>();
        repo.findAll().forEach(e->listOfEmployee.add(e));
        return listOfEmployee;
    }

    @Override
    public Employee viewEmployeeById(Long id) {
        return repo.findById(id).isPresent()? repo.findById(id).get():null;
    }

    @Override
    public void deleteEmployeeFromCache() {
         repo.deleteAll();
    }

    @Override
    public Mono<Employee> saveEmployeeToCache(EmployeeRequestDto employeeRequestDto) {
        return Mono.create(consumer-> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeRequestDto, employee);
            repo.save(employeeRequestDto.getId(),employee);
        });
    }
}
