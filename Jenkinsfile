pipeline {
    agent any // Use any available agent
    stages {
        stage('Check npm Version') {
            agent {
                docker {
                    image 'node:18' // Use Node.js image for this stage
                }
            }
            steps {
                sh 'npm --version' // Check npm version
            }
        }
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.8.5-openjdk-17' // Use Maven image for this stage
                }
            }
            steps {
                sh 'mvn clean install' // Package the project
            }
        }
    }
}

