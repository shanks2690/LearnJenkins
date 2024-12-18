
    pipeline {
        agent {
            docker {
                image 'node:18' // Using a Node.js Docker image that includes npm
            }
        }
        stages {
            stage('Check npm Version') {
                steps {
                    sh 'npm --version'
                }
            }
        }
    }
