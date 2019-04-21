// Scripted Pipeline
node {
    stage('Source') {
        git branch: 'master', url: 'https://github.com/cookcodeblog/jenkins-pipeline-examples.git'
    }
}