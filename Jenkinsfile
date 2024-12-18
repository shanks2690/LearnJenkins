
    pipeline {
        agent {
            docker {
                image 'node:18' // Using a Node.js Docker image that includes npm
            }
        }
        stages {
            stage('Check npm Version') {
                sh 'npm --version'
            }
            stage('package project ') {
                steps {
                    sh 'mvn clean install'
                }

            }
        }
    }
