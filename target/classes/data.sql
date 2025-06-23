INSERT INTO Project (id, name, description, status, created_at, updated_at)
VALUES
(1, 'Project Alpha', 'A new product development project for Q3.', 'ACTIVE', '2025-06-01 08:00:00', '2025-06-01 08:30:00'),
(2, 'Project Beta', 'Research and development of a new mobile app.', 'PLANNING', '2025-05-20 09:00:00', '2025-06-10 11:00:00'),
(3, 'Project Gamma', 'Update the company website with new features.', 'COMPLETED', '2025-03-15 10:00:00', '2025-05-10 14:00:00'),
(4, 'Project Delta', 'Company-wide internal system upgrade.', 'ON_HOLD', '2025-04-10 12:00:00', '2025-04-12 16:00:00'),
(5, 'Project Epsilon', 'Internal rebranding project for the marketing team.', 'ACTIVE', '2025-06-05 13:00:00', '2025-06-05 13:30:00');


INSERT INTO Task (id, project_id, title, description, status, priority, created_at, updated_at)
VALUES
(1, 1, 'Task 1: Market Research', 'Research on current market trends for the product.', 'IN_PROGRESS', 'HIGH', '2025-06-01 09:00:00', '2025-06-01 09:30:00'),
(2, 1, 'Task 2: Product Design', 'Initial wireframes for the product prototype.', 'PENDING', 'MEDIUM', '2025-06-01 09:30:00', '2025-06-01 09:45:00'),
(3, 2, 'Task 1: Competitive Analysis', 'Analyze competitors in the mobile app industry.', 'PENDING', 'HIGH', '2025-05-20 09:30:00', '2025-05-20 10:00:00'),
(4, 2, 'Task 2: Feature Planning', 'Define core features for the new mobile app.', 'IN_PROGRESS', 'MEDIUM', '2025-05-21 10:00:00', '2025-05-21 10:30:00'),
(5, 3, 'Task 1: Redesign Homepage', 'Redesign homepage UI to improve user engagement.', 'COMPLETED', 'HIGH', '2025-03-16 11:00:00', '2025-03-17 12:00:00'),
(6, 3, 'Task 2: Content Update', 'Update website content for new product launch.', 'COMPLETED', 'MEDIUM', '2025-04-01 10:00:00', '2025-04-02 11:00:00'),
(7, 4, 'Task 1: Requirements Gathering', 'Gather requirements for the new system update.', 'PENDING', 'LOW', '2025-04-10 13:00:00', '2025-04-10 13:30:00'),
(8, 5, 'Task 1: Design Branding Assets', 'Design new logo and branding elements.', 'IN_PROGRESS', 'HIGH', '2025-06-05 14:00:00', '2025-06-05 14:30:00'),
(9, 5, 'Task 2: Internal Communication', 'Develop internal communication strategy for rebranding.', 'PENDING', 'MEDIUM', '2025-06-06 09:00:00', '2025-06-06 09:15:00');