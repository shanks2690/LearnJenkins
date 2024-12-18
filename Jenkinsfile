pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                }
            }
//            steps {
//                sh '''
//                mvn clean install -Dmaven.repo.local=/tmp/maven-repo
//                java -jar target/LearnJenkins-0.0.1-SNAPSHOT.jar
//                '''
//            }
        }
    }
}
