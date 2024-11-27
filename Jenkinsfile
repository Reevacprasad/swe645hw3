pipeline {
	agent any
	environment {
		DOCKERHUB_PASS = credentials('docker-pass')
		BUILD_TIMESTAMP = new Date().format("yyyyMMdd-HHmmss", TimeZone.getTimeZone("UTC"))
	}
	stages {
		stage("Building the Springboot application image") {
			steps {
				script {
					checkout scm
					sh 'echo ${BUILD_TIMESTAMP}'
					sh 'docker login -u rprasad6 -p ${DOCKERHUB_PASS_PSW}'
					sh 'mvn clean package'
					def customImage = docker.build("rprasad6/hw3:${BUILD_TIMESTAMP}")
				}
			}
		}
		stage("Pushing image to Dockerhub") {
			steps {
				script {
					sh 'docker push rprasad6/hw3:${BUILD_TIMESTAMP}'
				}
			}
		}
		stage("Deploying to Rancher as single pod") {
			steps {
				sh 'kubectl set image deployment/deployment-hw3 container-hw3=rprasad6/hw3:${BUILD_TIMESTAMP} -n default'
			}
		}
	}
}