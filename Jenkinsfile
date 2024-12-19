pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '-v /Users/shanks2690/.m2/repository'
                }
            }
            steps {
                sh '''
                mvn clean install
                '''
            }
        }
        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t learnjenkins:latest .
                '''
            }
        }
    }
}
