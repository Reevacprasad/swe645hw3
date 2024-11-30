pipeline {
    agent any
    environment {
        DOCKERHUB_PASS = credentials('docker-pass') // DockerHub credentials
        BUILD_TIMESTAMP = new Date().format("yyyyMMdd-HHmmss", TimeZone.getTimeZone("UTC"))
    }
    stages {
        stage("Building the Spring Boot Application Image") {
            steps {
                script {
                    checkout scm
                    echo "Build timestamp: ${BUILD_TIMESTAMP}"

                    // Login to DockerHub
                    sh '''
                        echo "${DOCKERHUB_PASS_PSW}" | docker login -u gopalchada10010 --password-stdin
                    '''

                    // Build the application using Maven
                    sh 'mvn clean package'

                    // Build the Docker image
                    sh "docker build -t gopalchada10010/swe645:01-${BUILD_TIMESTAMP} ."
                }
            }
        }
        stage("Pushing Image to DockerHub") {
            steps {
                script {
                    // Push the image to DockerHub
                    sh "docker push gopalchada10010/swe645:01-${BUILD_TIMESTAMP}"
                }
            }
        }
        stage("Deploy to Kubernetes") {
            steps {
                // Inject kubeconfig from Jenkins credentials store
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {

                    // Update the Kubernetes deployment with the new image
                    sh "kubectl set image deployment/deployment-hw3 container-hw3=gopalchada10010/swe645:01-${BUILD_TIMESTAMP} -n default"
                }
            }
        }
    }
}
