package com.example.springbootdemo.repository.search;

import com.example.springbootdemo.qtype.QDepartment;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

public enum DepartmentSortColumn {
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    NAME("name");

    private String columnName;

    DepartmentSortColumn(String columnName) {
        this.columnName = columnName;
    }

    public static DepartmentSortColumn fromColumnName(String columnName) {
        for (DepartmentSortColumn col : values()) if (col.columnName.equals(columnName)) return col;
        throw new IllegalArgumentException(String.format(
                "No column with specified name or sorting is not supported by this column: %s", columnName));
    }

    public ComparableExpressionBase<?> toQDepartmentColumn() {
        return switch (this) {
            case CREATED_AT -> QDepartment.department.createdAt;
            case UPDATED_AT -> QDepartment.department.updatedAt;
            case NAME -> QDepartment.department.name;
        };
    }
}
