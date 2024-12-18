pipeline {
    agent any
    stages {
        stage('Check npm Version') {
            agent {
                docker {
                    image 'node:18'
                }
            }
            steps {
                sh 'npm --version'
            }
        }
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.3-eclipse-temurin-21' // Correct Maven image for JDK 21
                }
            }
            steps {
                sh '''
                mvn clean install
                java -jar target/LearnJenkins-0.0.1-SNAPSHOT.jar
                '''
            }
        }
    }
}
