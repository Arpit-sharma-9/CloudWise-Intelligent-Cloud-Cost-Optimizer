-- CloudWise Database Schema

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'USER', 'FINANCE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- AWS Resources Table
CREATE TABLE IF NOT EXISTS aws_resources (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    resource_type ENUM('EC2', 'S3', 'RDS', 'EBS', 'LAMBDA', 'VPC', 'ELASTIC_IP') NOT NULL,
    resource_id VARCHAR(100) NOT NULL,
    resource_name VARCHAR(100),
    region VARCHAR(50),
    cost DECIMAL(10, 2) DEFAULT 0.00,
    cpu_utilization DECIMAL(5, 2),
    memory_usage DECIMAL(5, 2),
    storage_gb DECIMAL(10, 2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Recommendations Table
CREATE TABLE IF NOT EXISTS recommendations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resource_id BIGINT NOT NULL,
    recommendation_type ENUM('RIGHTSIZE', 'STOP', 'DELETE', 'MOVE_STORAGE', 'DOWN_SIZE') NOT NULL,
    description TEXT NOT NULL,
    estimated_savings DECIMAL(10, 2) DEFAULT 0.00,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'EXECUTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (resource_id) REFERENCES aws_resources(id) ON DELETE CASCADE
);

-- Cost History Table
CREATE TABLE IF NOT EXISTS cost_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert Sample Data (Optional)
INSERT INTO users (username, password, role) VALUES 
('admin', '$2a$10$someHashedPassword', 'ADMIN'),
('user1', '$2a$10$someHashedPassword', 'USER');

INSERT INTO aws_resources (user_id, resource_type, resource_id, resource_name, region, cost, cpu_utilization, is_active) VALUES 
(1, 'EC2', 'i-1234567890', 'Web Server', 'us-east-1', 72.00, 3.00, TRUE),
(1, 'S3', 'my-bucket', 'Data Bucket', 'us-east-1', 120.00, NULL, TRUE),
(1, 'RDS', 'db-1234567890', 'Database', 'us-east-1', 310.00, NULL, TRUE);
