package com.cloudwise.config;

import com.cloudwise.entity.CloudResource;
import com.cloudwise.repository.CloudResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DataInitializer inserts sample cloud resources into the H2 database
 * automatically when the application starts.
 *
 * <p>The application uses an in-memory database, so the data is lost on
 * every restart. This class ensures that fresh sample data is always
 * available for testing and demos.</p>
 */
@Configuration
public class DataInitializer {

    /**
     * CommandLineRunner that runs once the Spring context is ready.
     * It populates 10 sample cloud resources if the database is empty.
     *
     * @param repository the CloudResourceRepository injected by Spring
     * @return a CommandLineRunner bean
     */
    @Bean
    public CommandLineRunner seedSampleData(CloudResourceRepository repository) {
        return args -> {
            // Only seed if the database is empty - avoids duplicates on restart
            // (not strictly necessary with H2 in-memory, but a good practice).
            if (repository.count() > 0) {
                return;
            }

            repository.save(createResource("EC2 Web Server", "EC2", "Mumbai", "Running", 4500.0));
            repository.save(createResource("EC2 Database Server", "EC2", "Mumbai", "Running", 8500.0));
            repository.save(createResource("EC2 Development Server", "EC2", "Delhi", "Stopped", 2500.0));
            repository.save(createResource("S3 Backup Bucket", "S3", "Mumbai", "Running", 900.0));
            repository.save(createResource("S3 Logs Bucket", "S3", "Delhi", "Running", 700.0));
            repository.save(createResource("RDS Production Database", "RDS", "Mumbai", "Running", 12000.0));
            repository.save(createResource("RDS Testing Database", "RDS", "Delhi", "Stopped", 3200.0));
            repository.save(createResource("Elastic Load Balancer", "ELB", "Mumbai", "Running", 2800.0));
            repository.save(createResource("EBS Volume", "EBS", "Mumbai", "Running", 600.0));
            repository.save(createResource("Lambda Function", "Lambda", "Delhi", "Running", 400.0));

            System.out.println("==> CloudWise: 10 sample cloud resources inserted into the database.");
        };
    }

    /**
     * Helper method that builds a CloudResource entity and generates
     * its recommendation automatically.
     */
    private CloudResource createResource(String name, String type, String region,
                                         String status, double monthlyCost) {
        CloudResource resource = new CloudResource();
        resource.setResourceName(name);
        resource.setResourceType(type);
        resource.setRegion(region);
        resource.setStatus(status);
        resource.setMonthlyCost(monthlyCost);
        resource.generateRecommendation();
        return resource;
    }
}
