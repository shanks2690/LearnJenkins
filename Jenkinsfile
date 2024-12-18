pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '-v /Users/$(whoami)/.m2:/root/.m2'
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
