package com.example.ignite.controller;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.Employee;
import com.example.ignite.service.IgniteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping(path = "/ignite")
public class IgniteEmployeeController {

    @Autowired
    IgniteEmployeeService igniteEmployeeService;

    @PostMapping("/saveEmployee")
    public String saveEmployeeToCache(@RequestBody EmployeeRequestDto employeeRequestDto) {
        igniteEmployeeService.saveEmployeeToCache(employeeRequestDto).subscribeOn(Schedulers.parallel()).subscribe();
        return "Data Saved";
    }

    @GetMapping("/viewEmployee")
    public List<Employee> viewEmployeeFromCache(){
        List<Employee> employeeList =  igniteEmployeeService.viewEmployeeFromCache();
        return employeeList;
    }

    @GetMapping("/employeeById")
    public Employee viewEmployeeById(@RequestParam("id") Long id){
        return igniteEmployeeService.viewEmployeeById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllEmployee() {
        igniteEmployeeService.deleteEmployeeFromCache();
        return "Deleted";
    }
}
