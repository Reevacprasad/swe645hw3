# Student Survey Management System Documentation

## Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
5. [Database Schema](#database-schema)
6. [API Endpoints](#api-endpoints)
7. [Configuration](#configuration)
8. [CI/CD Pipeline](#cicd-pipeline)
9. [Deployment](#deployment)
10. [Development Setup](#development-setup)
11. [Usage Examples](#usage-examples)
12. [Troubleshooting](#troubleshooting)

## Project Overview

The Student Survey Management System is a Spring Boot REST API application designed to collect and manage student survey data. The system allows for creating, reading, updating, and deleting student survey responses through RESTful endpoints.

**Group:** Alpha1  
**Application Name:** hw3  
**Version:** 0.0.1-SNAPSHOT

### Key Features
- Complete CRUD operations for student survey data
- RESTful API design
- MySQL database integration
- Docker containerization
- Kubernetes deployment
- Automated CI/CD pipeline with Jenkins
- Exception handling with custom exceptions

## Architecture

The application follows a layered architecture pattern:

```
┌─────────────────┐
│   Controller    │ ← REST API Layer
├─────────────────┤
│    Service      │ ← Business Logic Layer
├─────────────────┤
│   Repository    │ ← Data Access Layer
├─────────────────┤
│     Model       │ ← Entity Layer
└─────────────────┘
```

## Technologies Used

### Backend Framework
- **Spring Boot 3.3.1** - Main application framework
- **Spring Data JPA** - Data persistence
- **Spring Web** - REST API development
- **Java 17** - Programming language

### Database
- **MySQL 8** - Primary database
- **AWS RDS** - Database hosting

### DevOps & Deployment
- **Docker** - Containerization
- **Kubernetes** - Container orchestration
- **Jenkins** - CI/CD pipeline
- **DockerHub** - Container registry

### Development Tools
- **Maven** - Dependency management and build tool
- **OpenJDK 23** - Runtime environment (in Docker)

## Project Structure

```
src/
├── main/
│   ├── java/swe645/hw3/
│   │   ├── Hw3Application.java           # Main application class
│   │   ├── controller/
│   │   │   └── StudentController.java    # REST controller
│   │   ├── service/
│   │   │   ├── StudentService.java       # Service interface
│   │   │   └── impl/
│   │   │       └── StudentServiceImpl.java # Service implementation
│   │   ├── repository/
│   │   │   └── StudentRepository.java    # Data repository
│   │   ├── model/
│   │   │   └── Student.java              # Entity model
│   │   └── exception/
│   │       └── ResourceNotFoundException.java # Custom exception
│   └── resources/
│       └── application.properties        # Configuration
├── deployment.yaml                       # Kubernetes deployment
├── service.yaml                         # Kubernetes service
├── dockerfile                           # Docker configuration
├── Jenkinsfile                          # CI/CD pipeline
└── pom.xml                              # Maven configuration
```

## Database Schema

### Students Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| first_name | VARCHAR | NOT NULL | Student's first name |
| last_name | VARCHAR | NOT NULL | Student's last name |
| email | VARCHAR | NOT NULL | Student's email address |
| street_address | VARCHAR | NOT NULL | Street address |
| city | VARCHAR | NOT NULL | City |
| state | VARCHAR | NOT NULL | State |
| zip | VARCHAR | NOT NULL | ZIP code |
| telephone_number | VARCHAR | NOT NULL | Phone number |
| date_of_survey | DATE | NOT NULL | Survey completion date |
| liked_most | VARCHAR | NOT NULL | What student liked most |
| interest_source | VARCHAR | NOT NULL | How student heard about program |
| recommendation_likelihood | VARCHAR | NOT NULL | Likelihood to recommend |

### Database Connection
- **Host:** swe645hw3.coqjjbgylstn.us-east-1.rds.amazonaws.com
- **Database:** hw3db
- **Port:** 3306 (default MySQL port)

## API Endpoints

### Base URL
```
http://your-domain/api/students
```

### Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/students/hello` | Test endpoint | None | Welcome message |
| POST | `/api/students` | Create new student | Student JSON | Created student |
| GET | `/api/students` | Get all students | None | List of students |
| GET | `/api/students/{id}` | Get student by ID | None | Student object |
| PUT | `/api/students/{id}` | Update student | Student JSON | Updated student |
| DELETE | `/api/students/{id}` | Delete student | None | Success message |

### Sample Request/Response

#### Create Student (POST /api/students)
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "streetAddress": "123 Main St",
  "city": "Fairfax",
  "state": "VA",
  "zip": "22030",
  "telephoneNumber": "555-0123",
  "dateOfSurvey": "2024-08-04",
  "likedMost": "Campus facilities",
  "interestSource": "Website",
  "recommendationLikelihood": "Very Likely"
}
```

#### Response
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "streetAddress": "123 Main St",
  "city": "Fairfax",
  "state": "VA",
  "zip": "22030",
  "telephoneNumber": "555-0123",
  "dateOfSurvey": "2024-08-04",
  "likedMost": "Campus facilities",
  "interestSource": "Website",
  "recommendationLikelihood": "Very Likely"
}
```

## Configuration

### Application Properties
```properties
# Application name
spring.application.name=hw3

# Database configuration
spring.datasource.url=jdbc:mysql://swe645hw3.coqjjbgylstn.us-east-1.rds.amazonaws.com/hw3db
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Docker Configuration
The application runs in a Docker container using OpenJDK 23 slim image on port 8080.

## CI/CD Pipeline

### Jenkins Pipeline Stages

1. **Build Stage**
   - Checkout source code from SCM
   - Build application using Maven (`mvn clean package`)
   - Build Docker image with timestamp tag
   - Login to DockerHub

2. **Push Stage**
   - Push Docker image to DockerHub repository
   - Image naming convention: `gopalchada10010/swe645:01-{timestamp}`

3. **Deploy Stage**
   - Update Kubernetes deployment with new image
   - Deploy to `default` namespace
   - Use kubectl to set new image

### Environment Variables
- `DOCKERHUB_PASS`: DockerHub credentials
- `BUILD_TIMESTAMP`: UTC timestamp for image tagging

### Credentials Required
- `docker-pass`: DockerHub password
- `kubeconfig`: Kubernetes configuration file

## Deployment

### Kubernetes Configuration

#### Deployment (deployment.yaml)
- **Replicas:** 3 instances
- **Container Port:** 8080
- **Image:** gopalchada10010/swe645:01
- **Selector:** app: nginx

#### Service (service.yaml)
- **Type:** NodePort
- **Port:** 80 (external) → 8080 (container)
- **Protocol:** TCP

### Cluster Information
- **Cluster Name:** hw3-cluster
- **Server:** https://ec2-34-237-54-212.compute-1.amazonaws.com/k8s/clusters/c-m-hw7g8fkp
- **Namespace:** default

## Development Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker
- MySQL database access

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hw3
   ```

2. **Update database configuration**
   ```properties
   # For local development, update application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hw3db
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. **Build the application**
   ```bash
   ./mvnw clean package
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Test the application**
   ```bash
   curl http://localhost:8080/api/students/hello
   ```

### Docker Development

1. **Build Docker image**
   ```bash
   docker build -t hw3-app .
   ```

2. **Run container**
   ```bash
   docker run -p 8080:8080 hw3-app
   ```

## Usage Examples

### Using cURL

#### Test connectivity
```bash
curl http://your-domain/api/students/hello
```

#### Create a new student
```bash
curl -X POST http://your-domain/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "streetAddress": "456 Oak Ave",
    "city": "Arlington",
    "state": "VA",
    "zip": "22201",
    "telephoneNumber": "555-0456",
    "dateOfSurvey": "2024-08-04",
    "likedMost": "Faculty",
    "interestSource": "Friend",
    "recommendationLikelihood": "Likely"
  }'
```

#### Get all students
```bash
curl http://your-domain/api/students
```

#### Get student by ID
```bash
curl http://your-domain/api/students/1
```

#### Update student
```bash
curl -X PUT http://your-domain/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Johnson",
    "email": "jane.johnson@example.com",
    ...
  }'
```

#### Delete student
```bash
curl -X DELETE http://your-domain/api/students/1
```

## Troubleshooting

### Common Issues

#### Database Connection Issues
- Verify MySQL server is running
- Check firewall settings for RDS access
- Validate credentials in application.properties
- Ensure security groups allow inbound connections

#### Docker Build Failures
- Check if target/*.jar file exists after Maven build
- Verify Docker daemon is running
- Ensure sufficient disk space

#### Kubernetes Deployment Issues
- Verify kubeconfig is correctly configured
- Check if cluster is accessible
- Validate deployment and service YAML files
- Check pod logs: `kubectl logs deployment/deployment-hw3`

#### Jenkins Pipeline Failures
- Verify credentials are properly configured
- Check DockerHub connectivity
- Ensure kubectl is available in Jenkins environment
- Review build logs for specific error messages

### Debugging Commands

#### Check application status
```bash
kubectl get pods
kubectl get services
kubectl describe deployment deployment-hw3
```

#### View application logs
```bash
kubectl logs -f deployment/deployment-hw3
```

#### Test database connectivity
```bash
# From application pod
kubectl exec -it <pod-name> -- /bin/bash
# Test connection to MySQL
```

### Error Responses

#### 404 Not Found
```json
{
  "timestamp": "2024-08-04T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found here with Id : '123'",
  "path": "/api/students/123"
}
```

#### 500 Internal Server Error
- Check application logs
- Verify database connectivity
- Validate request payload format
