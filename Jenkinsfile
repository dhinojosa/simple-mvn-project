pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        script {
          if (isUnix()) {
            sh "mvn package"
          } else {
            bat "mvn package"
          }
        }

        tool(name: 'apache-maven-3.5.2', type: 'maven')
      }
    }
  }
}