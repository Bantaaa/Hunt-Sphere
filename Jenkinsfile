pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'huntsphere'
        DOCKER_TAG = 'latest'
        SONAR_PROJECT_KEY = 'huntsphere'
        APP_PORT = '8081'
        // Add email recipients
        EMAIL_RECIPIENTS = 'banta4code@gmail.com'
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
                    bat "docker run -d -p ${APP_PORT}:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
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
            emailext (
                subject: "Pipeline Success: ${currentBuild.fullDisplayName}",
                body: """
                    Pipeline execution completed successfully!

                    Build Number: ${currentBuild.number}
                    Build URL: ${env.BUILD_URL}
                    Project: ${env.JOB_NAME}

                    Changes:
                    ${currentBuild.changeSets.size() > 0 ? currentBuild.changeSets.collect { it.toString() }.join('\n') : 'No changes'}
                """,
                recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                to: "${EMAIL_RECIPIENTS}"
            )
        }
        failure {
            echo 'Build failed!'
            emailext (
                subject: "Pipeline Failed: ${currentBuild.fullDisplayName}",
                body: """
                    Pipeline execution failed!

                    Build Number: ${currentBuild.number}
                    Build URL: ${env.BUILD_URL}
                    Project: ${env.JOB_NAME}

                    Console Output:
                    ${currentBuild.rawBuild.getLog(100).join('\n')}
                """,
                recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                to: "${EMAIL_RECIPIENTS}"
            )
        }
    }
}