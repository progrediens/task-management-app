# task-management-app
ALTER TABLE SOME_TABLE
ADD CONSTRAINT enumcheck CHECK (status_enum IN ('ERROR', 'CONFIRMED', 'QUEUED', 'REJECTED'));