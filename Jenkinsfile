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

        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t shanks_jenkins .'
            }
        }

        stage('Push to ECR') {
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
                        AWS_REGION="ap-south-1"
                        AWS_ACCOUNT_ID="863518451855"
                        REPO_NAME="shanks_jenkins"
                        IMAGE_TAG="latest"

                        # Login to ECR
                        aws ecr get-login-password --region $AWS_REGION | \
                          docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

                        # Tag and push
                        docker tag shanks_jenkins:$IMAGE_TAG $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$REPO_NAME:$IMAGE_TAG
                        docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$REPO_NAME:$IMAGE_TAG
                    '''
                }
            }
        }
    }
}
