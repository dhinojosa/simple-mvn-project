pipeline {
  agent any
  stages {
    stage('Unit Testing') {
      steps {
        build 'Unit Testing'
        sh 'sh "${mvnHome}/bin/mvn -B verify"'
      }
    }
  }
}