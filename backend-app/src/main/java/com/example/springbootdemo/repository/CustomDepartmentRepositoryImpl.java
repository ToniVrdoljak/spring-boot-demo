package com.example.springbootdemo.repository;

import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.model.Department;
import com.example.springbootdemo.qtype.QDepartment;
import com.example.springbootdemo.qtype.QEmployee;
import com.example.springbootdemo.repository.search.DepartmentSearchUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.*;

public class CustomDepartmentRepositoryImpl implements CustomDepartmentRepository {

    private static final QDepartment department = QDepartment.department;
    private static final QEmployee employee = QEmployee.employee;

    private JPAQueryFactory queryFactory;

    @PersistenceContext
    public void setQueryFactory(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /*
    This is an example of how to write queries for complete department objects with pagination. First we write a query
    that selects only QDepartment.department with appropriate filters and then we pass it to findByQuery method.
     */
    @Override
    public Page<Department> findByDepartmentSearch(
            DepartmentSearchCriteria departmentSearchCriteria, Pageable pagination) {

        JPAQuery<Department> query = queryFactory
                .select(department)
                .from(department)
                .where(DepartmentSearchUtils.transformToPredicate(departmentSearchCriteria));

        return findByQuery(query, pagination);
    }

    /*
    This method is to be used for all complex queries (assembling the whole department object) that require pagination.
    Using the naive approach or using the built-in methods Spring Data JPA pagination methods can result in in-memory
    pagination or N+1 query problem. The method all ready does all the necessary logic for assembling department objects.
     */
    private Page<Department> findByQuery(JPAQuery<Department> query, Pageable pagination) {
        Long count = getDepartmentCount(query.clone());
        List<Long> ids = getDepartmentIds(query, pagination);
        List<Department> result = getDepartmentsByIds(ids, pagination.getSort());

        return new PageImpl<>(result, pagination, count);
    }

    private Long getDepartmentCount(JPAQuery<?> query) {
        return query.select(department.count()).fetchOne();
    }

    private List<Long> getDepartmentIds(JPAQuery<?> query, Pageable pagination) {
        JPAQuery<Long> countQuery =
                query.select(department.id).offset(pagination.getOffset()).limit(pagination.getPageSize());
        DepartmentSearchUtils.sort(countQuery, pagination.getSort());

        return countQuery.fetch();
    }

    private List<Department> getDepartmentsByIds(List<Long> ids, Sort sort) {

        JPAQuery<Department> query = queryFactory
                .select(department)
                .from(department)
                .where(department.id.in(ids))
                .leftJoin(department.employees, employee)
                .fetchJoin()
                .leftJoin(employee.jobRoles)
                .fetchJoin();

        DepartmentSearchUtils.sort(query, sort);

        return query.fetch();
    }
}
