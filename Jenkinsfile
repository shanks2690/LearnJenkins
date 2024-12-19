pipeline {
    agent any

    stages {
        stage('Package Project') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-21'
                    // Running as root user and mounting a host directory as Maven local repo cache
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

        stage('Push to ECR') {
            // This stage uses a Docker CLI image and mounts the Docker socket from the host
            // so it can use the host's Docker daemon.
            // It then installs AWS CLI to interact with ECR.
            agent {
                docker {
                    image 'docker:24.0.5-cli'
                    // Mounting the host docker socket for Docker commands
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                // Provide AWS creds to the environment
                withCredentials([usernamePassword(credentialsId: 'aws-creds',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY',
                        usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
                    sh '''
                        # Install AWS CLI v2 inside the container
                        # Alpine-based, so we use apk and curl
                        apk add --no-cache curl unzip
                        curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
                        unzip awscliv2.zip
                        ./aws/install

                        # Check installations
                        aws --version
                        docker --version

                        # Variables (adjust these as needed)
                        AWS_REGION="ap-south-1"
                        AWS_ACCOUNT_ID="863518451855"
                        REPO_NAME="shanks_jenkins"
                        IMAGE_TAG="latest"

                        # Login to ECR
                        aws ecr get-login-password --region $AWS_REGION | \
                          docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

                        # Tag and push image
                        docker tag shanks_jenkins:$IMAGE_TAG $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$REPO_NAME:$IMAGE_TAG
                        docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$REPO_NAME:$IMAGE_TAG
                    '''
                }
            }
        }
    }
}
