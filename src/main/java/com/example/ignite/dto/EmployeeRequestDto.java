package com.example.ignite.dto;

import lombok.Data;

@Data
public class EmployeeRequestDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String department;
}
