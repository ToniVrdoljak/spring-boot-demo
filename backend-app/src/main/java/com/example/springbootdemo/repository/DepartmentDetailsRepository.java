package com.example.springbootdemo.repository;

import com.example.springbootdemo.dto.DepartmentDetails;
import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentDetailsRepository {
    Page<DepartmentDetails> findAll(DepartmentSearchCriteria departmentSearchCriteria, Pageable pageable);

    DepartmentDetails findById(long id);
}
