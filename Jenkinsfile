pipeline {
    agent any
    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    args '--user root -v /tmp/maven-repo:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean install -Dmaven.repo.local=/root/.m2'
            }
        }


        stage('Push to S3') {
            // Use the AWS CLI image directly
            agent {
                docker {
                    image 'amazon/aws-cli:2.13.14' // Adjust to latest or desired version
                    args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws-creds', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
                    sh '''
                        aws s3 ls
                    '''
                }
            }
        }
    }
}
