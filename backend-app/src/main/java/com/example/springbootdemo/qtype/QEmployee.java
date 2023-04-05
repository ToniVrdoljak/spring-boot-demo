package com.example.springbootdemo.qtype;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.model.JobRole;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 1746711118L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final DateTimePath<java.time.ZonedDateTime> createdAt =
            createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final QDepartment department;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<JobRole, QJobRole> jobRoles =
            this.<JobRole, QJobRole>createSet("jobRoles", JobRole.class, QJobRole.class, PathInits.DIRECT2);

    public final StringPath lastname = createString("lastname");

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.ZonedDateTime> updatedAt =
            createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department")) : null;
    }
}
