package com.example.springbootdemo;

import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.model.Department;
import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.repository.DepartmentDetailsRepository;
import com.example.springbootdemo.repository.DepartmentRepository;
import com.example.springbootdemo.repository.EmployeeRepository;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;

@SpringBootApplication
public class SpringBootDemoApplication {

    @Autowired
    private DepartmentRepository repo;

    @Autowired
    private EmployeeRepository erepo;

    @Autowired
    private DepartmentDetailsRepository ddr;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ApplicationContext ctx) {
        return args -> {
            System.out.println("##########################################");
            Employee e = new Employee();
            e.setName("Toni");
            e.setLastname("Vrdoljak");
            e.setCreatedAt(ZonedDateTime.now());
            e.setUpdatedAt(ZonedDateTime.now());
            Employee e2 = new Employee();
            e2.setName("Toni");
            e2.setLastname("Vrdoljak");
            e2.setCreatedAt(ZonedDateTime.now());
            e2.setUpdatedAt(ZonedDateTime.now());
            Department d = new Department();
            d.setName("dep");
            d.setEmployees(Collections.singleton(e));
            ZonedDateTime zdt = ZonedDateTime.now();
            System.out.println(zdt);
            d.setCreatedAt(zdt);
            d.setUpdatedAt(ZonedDateTime.now());
            e.setDepartment(d);
            e2.setDepartment(d);

            repo.save(d);
            erepo.save(e);
            erepo.save(e2);

            System.out.println("*******************************************");
            DepartmentSearchCriteria dp =
                    new DepartmentSearchCriteria(null, null, ZonedDateTime.parse("2023-04-07T09:36:10+02:00"), null);
            PageRequest pageable = PageRequest.of(0, 3, Sort.by("created_at").descending());

            var page = repo.findByDepartmentSearch(dp, pageable);

            System.out.println("##########################################");
            System.out.println(page);
            System.out.println(page.getTotalElements());
            System.out.println(page.getTotalPages());
            System.out.println(page.get().count());
            System.out.println(page.get().collect(Collectors.toUnmodifiableList()));

            System.out.println("##########################################");

            System.out.println(ddr.findById(1));
            System.out.println(ddr.findAll(dp, pageable).get().collect(Collectors.toUnmodifiableList()));
        };
    }
}
