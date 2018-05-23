pipeline {
  agent any

  stages {
    stage('Unit') {

      steps {
        def mvnHome = tool 'apache-maven-3.5.2'
        echo 'Unit'
      }
    }
    stage('Integration') {
      steps {
        echo 'Integration'
      }
    }
  }
}