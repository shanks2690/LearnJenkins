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
                '''
            }
        }
        stage('Push to S3') {
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
                    mkdir shanks
                     echo "Hi Kuku. Pushed to S3 from jenkins"> shanks/Kuku.txt
                     aws s3 sync shanks s3://shanks2690
             '''
                }

            }
        }
    }
}
