INSERT INTO project (id, name, status, created_at, updated_at)
VALUES (1, 'Build API', 'PLANNING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO task (project_id, title, status, priority, created_at, updated_at)
VALUES
(1, 'Design database schema', 'PENDING', 'HIGH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Implement endpoints', 'IN_PROGRESS', 'MEDIUM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
