package com.example.ignite.entity;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = -2974414641088735500L;

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String username;

    @QuerySqlField
    private String firstName;

    @QuerySqlField
    private String lastName;

    @QuerySqlField
    private String department;

    private String test;

    private String newColumn;

}
