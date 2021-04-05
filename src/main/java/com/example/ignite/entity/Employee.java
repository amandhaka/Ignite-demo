package com.example.ignite.entity;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = -2974414641088735500L;

    @QuerySqlField(index = true)
    public Long id;

    @QuerySqlField
    public String username;

    @QuerySqlField
    public String firstName;

    @QuerySqlField
    public String lastName;

    @QuerySqlField
    public String department;

    public String test;
}
