package com.example.springbootdemo.repository.search;

import com.example.springbootdemo.dto.DepartmentSearchCriteria;
import com.example.springbootdemo.qtype.QDepartment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Sort;

public class DepartmentSearchUtils {

    private static final QDepartment department = QDepartment.department;

    public static Predicate transformToPredicate(DepartmentSearchCriteria departmentSearchCriteria) {

        BooleanBuilder builder = new BooleanBuilder();

        if (departmentSearchCriteria.name() != null
                && !departmentSearchCriteria.name().isEmpty())
            builder.and(department.name.eq(departmentSearchCriteria.name()));

        if (departmentSearchCriteria.searchTerm() != null
                && !departmentSearchCriteria.searchTerm().isEmpty())
            builder.and(department
                    .description
                    .containsIgnoreCase(departmentSearchCriteria.searchTerm())
                    .or(department.name.containsIgnoreCase(departmentSearchCriteria.searchTerm())));

        if (departmentSearchCriteria.createdAtLower() != null)
            builder.and(department.createdAt.after(departmentSearchCriteria.createdAtLower()));

        if (departmentSearchCriteria.createdAtUpper() != null)
            builder.and(department.createdAt.before(departmentSearchCriteria.createdAtUpper()));

        return builder.getValue();
    }

    public static void sort(JPAQuery<?> query, Sort sort) {
        for (Sort.Order order : sort) {

            var column =
                    DepartmentSortColumn.fromColumnName(order.getProperty()).toQDepartmentColumn();

            query.orderBy(
                    switch (order.getDirection()) {
                        case ASC -> column.asc();
                        case DESC -> column.desc();
                    });
        }
    }
}
