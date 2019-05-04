node {
    stage('Source') {
        cleanWs()
        git branch: 'master', url: 'https://github.com/cookcodeblog/gs-rest-service.git'
    }
    stage('Build') {
        sh './gradlew clean build'
    }
}