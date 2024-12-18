pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'huntsphere'
        DOCKER_TAG = 'latest'
        SONAR_PROJECT_KEY = 'huntsphere'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
            post {
                success {
                    echo 'Maven build successful'
                }
                failure {
                    echo 'Maven build failed'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=sqa_a6d5a3ab7bad4f036f2c27f709a00259e0d6d610
                """
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Manual Validation') {
            steps {
                input message: 'Deploy to production?', ok: 'Deploy'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    bat "docker run -d -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}