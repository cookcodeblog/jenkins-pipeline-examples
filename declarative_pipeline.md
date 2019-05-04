[TOC]

# [pipeline](https://jenkins.io/doc/book/pipeline/syntax/#pipeline)

Snippet Generator: Pipeline syntax / Declarative Directive Generator

## [agent](https://jenkins.io/doc/book/pipeline/syntax/#agent)

Use Jenkins agents at pipeline level

Example:

```groovy
agent {label 'slave'}
agent {label 'docker-slave'}
```

## [environment](https://jenkins.io/doc/book/pipeline/syntax/#environment)

Jenkins environment variables for all sages

Define key-value pairs

Example:

```groovy
environment {
  PROJECT = "demo"
}
```

## [tools](https://jenkins.io/doc/book/pipeline/syntax/#tools)

Use build tools, e.g. Maven, Gradle, etc.

Tools are defined in Manage Jenkins / Global Tool Configuration

Example:

```groovy
tools {
  gradle 'gradle5.4'
}
```

## [options](https://jenkins.io/doc/book/pipeline/syntax/#options)

Define Jenkins job options

Example:

```groovy
options {
  // Discard old builds, keep 15 days build
  buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '15', numToKeepStr: '')
  // Do not allow concurrent build
  disableConcurrentBuilds()
  // Set the quiet period for the job as 5 seconds
  quietPeriod 5
  // Enforce time limit as 30 minutes
  timeout(30)
  // Add timestamps to the Console Output
  timestamps()
}
```

## [triggers](https://jenkins.io/doc/book/pipeline/syntax/#triggers)

Define Jenkins job build triggers

Example:

```groovy
triggers {
  cron 'H/15 * * * *'
}
```



## [parameters](https://jenkins.io/doc/book/pipeline/syntax/#parameters)

Define Jenkins job parameters

Can change parameters when click build with prameters

Example:

```groovy
parameters {
  // boolean parameter
  booleanParam defaultValue: false, description: '', name: 'SkipTest'
  // Mutli-choices parameter
  choice choices: 'Test Env\nPre-Prod Env\nProd Env', description: '', name: 'DeployTo'
  // String parameter
  string defaultValue: 'demo1', description: '', name: 'ProjectName', trim: true
}
```



## [libraries](https://jenkins.io/doc/book/pipeline/shared-libraries/)

Import shared libraries to reuse codes.

> TODO

## [stages](https://jenkins.io/doc/book/pipeline/syntax/#stages)

Define stages in Jenkins pipeline.

### [stage](https://jenkins.io/doc/book/pipeline/syntax/#stage)

Define a stage.

#### [agent](https://jenkins.io/doc/book/pipeline/syntax/#agent)

Use Jenkins agent at stage level.

See above `agent`.

#### [environment](https://jenkins.io/doc/book/pipeline/syntax/#environment)

Jenkins environment variables at stage level.

See above `environment`.

#### [tools](https://jenkins.io/doc/book/pipeline/syntax/#tools)

Use build tools.

See above `tools`.

#### [when](https://jenkins.io/doc/book/pipeline/syntax/#when)

When condition, skip the stage if when condition is false.

Example:

```groovy
parameters {
  // boolean parameter
  booleanParam defaultValue: false, description: '', name: 'SkipTest'
}

stage('Package') {
    when {
        expression {return params.SkipTest}
    }
    steps {
        sh 'mvn clean package -Dmaven.test.skip=true'
    }
}
```



Use parameters instead `when` statement.

Example:

```groovy
stage('Package') {
    steps {
        sh "mvn clean package -Dmaven.test.skip=${params.SkipTest}"
    }
}
```



Use `if/else` instead  of `when` statement.

Example:

```groovy
stage('Package') {
    steps {
        script {
            if (params.SkipTest == true) {
                sh "mvn clean package -Dmaven.test.skip=true"
            } else {
                sh "mvn clean package"
            }
        } 
    }
}
```



#### [input](https://jenkins.io/doc/book/pipeline/syntax/#input)

Wait user input in stage.

Example:

```groovy
stage('Deploy') {
    input {
        message 'Are you sure to deploy?'
        ok 'Deploy'
        parameters {
            choice choices: 'Dev\nQA\nPre-Prod\nProduction', description: 'Choose a target env', name: 'deployEnv'
        }
    }
    steps {
        echo "Deploy to ${deployEnv}"
    }
}
```



#### [steps](https://jenkins.io/doc/book/pipeline/syntax/#steps)

Jenkins steps block.

Put DSL statements in `steps`.

##### [script](https://jenkins.io/doc/book/pipeline/syntax/#script)

Jenkins scripted pipeline statements

#### [post](https://jenkins.io/doc/book/pipeline/syntax/#post)

Post actions at the end of stage.

Example:

```groovy
post {
  always {
    echo "Notify always"
  }
  aborted {
    echo "Notify aborted"
  }
  success {
    echo "Notify success"
  }
  failure {
    echo "Notify failure"
  }
}
```



## [post](https://jenkins.io/doc/book/pipeline/syntax/#post)

Post actions at the end of pipeline.

See above `post`.

