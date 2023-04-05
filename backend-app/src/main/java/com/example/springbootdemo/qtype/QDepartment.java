package com.example.springbootdemo.qtype;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.springbootdemo.model.Department;
import com.example.springbootdemo.model.Employee;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -62675214L;

    public static final QDepartment department = new QDepartment("department");

    public final DateTimePath<java.time.ZonedDateTime> createdAt =
            createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final StringPath description = createString("description");

    public final SetPath<Employee, QEmployee> employees =
            this.<Employee, QEmployee>createSet("employees", Employee.class, QEmployee.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.ZonedDateTime> updatedAt =
            createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public QDepartment(String variable) {
        super(Department.class, forVariable(variable));
    }

    public QDepartment(Path<? extends Department> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartment(PathMetadata metadata) {
        super(Department.class, metadata);
    }
}
