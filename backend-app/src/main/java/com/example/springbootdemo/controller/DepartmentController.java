package com.example.springbootdemo.controller;

import com.example.springbootdemo.dto.DepartmentDetails;
import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.model.Department;
import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.repository.DepartmentDetailsRepository;
import com.example.springbootdemo.repository.DepartmentRepository;
import com.example.springbootdemo.repository.EmployeeRepository;
import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    public DepartmentController(
            DepartmentRepository depRepository,
            EmployeeRepository empRepository,
            DepartmentDetailsRepository departmentDetailsRepository) {
        this.depRepository = depRepository;
        this.empRepository = empRepository;
        this.departmentDetailsRepository = departmentDetailsRepository;
    }

    private final DepartmentDetailsRepository departmentDetailsRepository;
    private final DepartmentRepository depRepository;
    private final EmployeeRepository empRepository;

    @PostMapping("api/departments")
    public long createDepartment(@RequestBody Department department) {
        ZonedDateTime now = ZonedDateTime.now();
        department.setCreatedAt(now);
        department.setUpdatedAt(now);
        depRepository.save(department);

        return department.getId();
    }

    @PostMapping("api/departments/{id}/employees")
    public long createEmployee(@PathVariable long id, @RequestBody Employee employee) {
        ZonedDateTime now = ZonedDateTime.now();
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);
        Department dep = depRepository.getReferenceById(id);
        employee.setDepartment(dep);
        empRepository.save(employee);

        return employee.getId();
    }

    @PostMapping("api/departments/search")
    public ResponseEntity<Page<DepartmentDetails>> findAll(
            @RequestBody DepartmentSearchCriteria departmentSearchCriteria, Pageable pageable) {
        Page<DepartmentDetails> departmentDetails =
                departmentDetailsRepository.findAll(departmentSearchCriteria, pageable);
        return ResponseEntity.ok(departmentDetails);
    }
}
