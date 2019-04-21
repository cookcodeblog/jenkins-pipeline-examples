// Declarative Pipeline
pipeline {
    agent any
    stages {
        stage('Source') {
            steps {
                git branch: 'master', url: 'https://github.com/cookcodeblog/jenkins-pipeline-examples.git'
            }
        }
    }
}