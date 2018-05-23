pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool(name: 'maven', type: 'apache-maven-3.25')
        script {
          if (isUnix()) {
            sh "mvn package"
          } else {
            bat "mvn package"
          }
        }

      }
    }
  }
}