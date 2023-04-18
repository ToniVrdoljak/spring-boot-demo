package com.example.springbootdemo.dto;

import java.time.ZonedDateTime;

public record DepartmentSearchCriteria(
        String name, String searchTerm, ZonedDateTime createdAtLower, ZonedDateTime createdAtUpper) {}
