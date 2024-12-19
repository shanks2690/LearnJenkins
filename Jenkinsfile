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
                sh '''
                mvn clean install -Dmaven.repo.local=/root/.m2
                docker build -t shanks_jenkins .
                '''
            }
        }
        stage('Prepare for AWS Deployment') {
            agent {
                docker {
                    image 'amazon/aws-cli'
                    args '--entrypoint=\'\''
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws-creds', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
                    sh'''
                    aws --version
                    aws s3 ls
                    
                    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 863518451855.dkr.ecr.ap-south-1.amazonaws.com
                docker tag shanks_jenkins:latest 863518451855.dkr.ecr.ap-south-1.amazonaws.com/shanks_jenkins:latest
                docker push 863518451855.dkr.ecr.ap-south-1.amazonaws.com/shanks_jenkins:latest
                '''
                }

            }
        }
    }
}
