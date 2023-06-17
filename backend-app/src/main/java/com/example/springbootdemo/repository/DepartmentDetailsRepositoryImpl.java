package com.example.springbootdemo.repository;

import com.example.springbootdemo.dto.DepartmentDetails;
import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.model.QEmployee;
import com.example.springbootdemo.qtype.QDepartment;
import com.example.springbootdemo.repository.search.DepartmentSearchUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDetailsRepositoryImpl implements DepartmentDetailsRepository {

    private static final QDepartment department = QDepartment.department;
    private static final QEmployee employee = QEmployee.employee;

    private JPAQueryFactory queryFactory;

    @PersistenceContext
    public void setQueryFactory(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<DepartmentDetails> findAll(DepartmentSearchCriteria departmentSearchCriteria, Pageable pageable) {

        Long count = queryFactory.select(department.count()).from(department).fetchOne();

        var query = queryFactory
                .select(Projections.constructor(
                        DepartmentDetails.class,
                        department.id,
                        department.name,
                        department.description,
                        employee.count()))
                .from(department)
                .leftJoin(department.employees, employee)
                .where(DepartmentSearchUtils.transformToPredicate(departmentSearchCriteria))
                .groupBy(department.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        DepartmentSearchUtils.sort(query, pageable.getSort());

        List<DepartmentDetails> departments = query.fetch();

        if (count != null) return new PageImpl<>(departments, pageable, count);
        else throw new RuntimeException("We should never get here since count should always be set");
    }

    @Override
    public DepartmentDetails findById(long id) {
        return queryFactory
                .select(Projections.constructor(
                        DepartmentDetails.class,
                        department.id,
                        department.name,
                        department.description,
                        employee.count()))
                .from(department)
                .leftJoin(department.employees, employee)
                .where(department.id.eq(id))
                .groupBy(department.id)
                .fetchOne();
    }
}
