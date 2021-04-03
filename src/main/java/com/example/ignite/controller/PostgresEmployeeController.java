package com.example.ignite.controller;

import com.example.ignite.dto.EmployeeRequestDto;
import com.example.ignite.entity.postgresql.Employee;
import com.example.ignite.service.PostgresEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping(value = "/postgres")
public class PostgresEmployeeController {

    @Autowired
    PostgresEmployeeService postgresEmployeeService;

    @PostMapping("/addEmployee")
    public String saveEmployeeToPostgresDb(@RequestBody EmployeeRequestDto employeeRequestDto) {
        postgresEmployeeService.saveEmployeeToPostgresDb(employeeRequestDto).subscribeOn(Schedulers.parallel()).subscribe();
        return "Saved";
    }

    @GetMapping("/viewEmployee")
    public List<Employee> viewEmployeeFromPostgresDb(){
        return postgresEmployeeService.viewEmployeeFromPostgresDb();
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployeefromPostgresDb() {
        postgresEmployeeService.deleteEmployeeFromPostgresDb().subscribeOn(Schedulers.parallel()).subscribe();
        return "DELETED";
    }
}
