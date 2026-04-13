USE securevault;

-- Insert sample users
INSERT IGNORE INTO users (username, role) VALUES 
('admin', 'ADMIN'),
('john_doe', 'USER'),
('jane_smith', 'USER');

-- Insert sample audit logs
INSERT IGNORE INTO audit_log (username, action, timestamp) VALUES 
('admin', 'System initialized', NOW()),
('admin', 'Added file: financial_report.pdf', NOW()),
('john_doe', 'Viewed file list', NOW());