// Declarative Pipeline
// Pipeline syntax / Declarative Directive Generator
// https://jenkins.io/doc/book/pipeline/syntax/#<key_word>
pipeline {
    // Use Jenkins agents
    // // https://jenkins.io/doc/book/pipeline/syntax/#<key_word>
    // agent {label 'slave'}
    // agent {label 'docker-slave'}
    agent {}
    // Jenkins environment variables for all stages
    // Define key-value
    environment {}
    // Use build tools, e.g. Maven, Gradle
    tools {}
    // Jenkins job options
    options {}
    // Jenkins job build triggers
    triggers {}
    // Jenkins job build parameters
    parameters {}
    // Shared libraries ?
    libraries {}
    // Stages
    stages {
        // Stage
        stage('Stage1') {
            agent {}
            // Jenkins environment variables per stage level
            environment {}
            tools {}
            // Pipeline steps
            steps {
                // DSL statements
                // Scripted pipeline
                script {

                }
            }
            // Post actions
            post {}
        }
        stage('Stage2') {
           // Another stage
        }
    }
    post {}
}