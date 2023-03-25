CREATE TABLE employees (
    id INT8 PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL,
    lastName TEXT NOT NULL,
    department_id INT8 NOT NULL REFERENCES departments(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT Now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT Now()
);

CREATE TABLE job_roles (
    id INT8 PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL,
    description TEXT
);

CREATE TABLE employees_job_roles (
    employee_id INT8 REFERENCES employees(id),
    job_role_id INT8 REFERENCES job_roles(id),
    PRIMARY KEY (employee_id, job_role_id)
);