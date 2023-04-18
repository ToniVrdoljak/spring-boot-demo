package com.example.springbootdemo.repository;

import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomDepartmentRepository {
    public Page<Department> findByDepartmentSearch(
            DepartmentSearchCriteria departmentSearchCriteria, Pageable pageable);
}
