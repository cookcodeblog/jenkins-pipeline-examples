pipeline {
    agent {label 'docker-slave'}

    stages {
        stage('Prepare') {
            steps {
                sh 'git --version'
                sh 'mvn -v'
            }
        }

        stage('Source') {
            steps {
                git branch: 'master', url: 'https://github.com/cookcodeblog/gs-rest-service.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }

        }
    }
}