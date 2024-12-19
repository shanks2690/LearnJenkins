pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '-v /tmp/maven-repo:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh '''
                mvn clean install -Dmaven.repo.local=/root/.m2
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
