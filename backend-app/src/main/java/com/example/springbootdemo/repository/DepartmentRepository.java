package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, CustomDepartmentRepository {}
