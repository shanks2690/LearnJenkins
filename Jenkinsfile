pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '-v /Users/shanks2690/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh '''
                mvn clean install
                '''
            }
        }
    }
}
