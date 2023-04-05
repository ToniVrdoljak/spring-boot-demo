package com.example.springbootdemo.qtype;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.model.JobRole;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/**
 * QJobRole is a Querydsl query type for JobRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobRole extends EntityPathBase<JobRole> {

    private static final long serialVersionUID = -1558664653L;

    public static final QJobRole jobRole = new QJobRole("jobRole");

    public final StringPath description = createString("description");

    public final SetPath<Employee, QEmployee> employees =
            this.<Employee, QEmployee>createSet("employees", Employee.class, QEmployee.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QJobRole(String variable) {
        super(JobRole.class, forVariable(variable));
    }

    public QJobRole(Path<? extends JobRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJobRole(PathMetadata metadata) {
        super(JobRole.class, metadata);
    }
}
