pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '-v $HOME/.m2:/root/.m2' // Mount host Maven repository
                    reuseNode true
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


//pipeline {
//    agent any
//    stages {
//        stage('Package Project') {
//            agent {
//                docker {
//                    image 'maven:3.9.5-eclipse-temurin-21'
//                    reuseNode true
//                }
//            }
//            steps {
//                sh '''
//                mvn clean install -Dmaven.repo.local=/tmp/maven-repo
//                #java -jar target/LearnJenkins-0.0.1-SNAPSHOT.jar
//                '''
//            }
//        }
//        stage('Build Docker Image') {
//            steps {
//                sh '''
//                docker build -t learnjenkins:latest .
//                '''
//            }
//        }
//    }
//}
