# Hunt-Sphere CI/CD Pipeline Documentation

## Overview
This repository contains a Jenkins CI/CD pipeline implementation for the Hunt-Sphere Java Spring Boot application. The pipeline automates building, testing, analyzing, and deploying the application using Docker containers.

## Prerequisites
- Jenkins server running on Windows
- SonarQube server (running on port 9000)
- Docker installed
- Maven installed
- Java 17 or higher

## Pipeline Stages

### 1. Checkout
- Pulls the latest code from the repository
- Uses the `pipeline` branch

### 2. Build
- Executes `mvn clean package -DskipTests`
- Builds the application without running tests
- Artifacts are stored in `target/` directory

### 3. SonarQube Analysis
- Performs code quality analysis
- Configuration:
  - Server URL: http://localhost:9000
  - Project Key: huntsphere

### 4. Docker Build
- Creates a Docker image named 'huntsphere:latest'
- Uses Dockerfile in root directory

### 5. Manual Validation
- Requires manual approval before deployment
- Prompts user with "Deploy to production?" message

### 6. Deploy
- Deploys the Docker container
- Exposes the application on port 8081

## Environment Variables
```
DOCKER_IMAGE = 'huntsphere'
DOCKER_TAG = 'latest'
SONAR_PROJECT_KEY = 'huntsphere'
APP_PORT = '8081'
```

## Jenkins Configuration

### Required Jenkins Plugins
- Docker Pipeline
- Pipeline
- Git

### Email Notifications
The pipeline sends email notifications for:
- Build Success
- Build Failure

Configure email settings in Jenkins:
1. Navigate to Manage Jenkins > System
2. Set System Admin email address
3. Configure SMTP settings if needed

## Deployment Process
1. Push code to the repository
2. Jenkins automatically triggers the pipeline
3. Monitor build progress in Jenkins UI
4. Review SonarQube analysis results
5. Approve deployment when prompted
6. Verify application at http://localhost:8081

## Maintenance

### Pipeline Updates
To modify the pipeline:
1. Edit `Jenkinsfile` in the repository root
2. Test changes in a feature branch
3. Merge to main branch when verified

### Common Issues
1. Docker Port Conflicts
   - Solution: Change APP_PORT in Jenkinsfile

2. SonarQube Connection
   - Verify SonarQube is running
   - Check token validity
   - Ensure correct URL configuration

### Security Notes
- SonarQube token should be stored securely
- Docker registry credentials (if used) should be configured in Jenkins credentials
- Regularly update dependencies and Docker base images

## Contributing
1. Create a feature branch
2. Make changes to pipeline configuration
3. Test thoroughly
4. Submit pull request
5. Await review and approval

## Contact
For pipeline issues or questions, contact the DevOps team.
