ALTER TABLE departments
    ADD description TEXT,
    ADD created_at TIMESTAMPTZ NOT NULL DEFAULT Now(),
    ADD updated_at TIMESTAMPTZ NOT NULL DEFAULT Now();