pipeline {
  agent any

  stages {
    stage('Unit') {
      def mvnHome = tool 'apache-maven-3.5.2'
      steps {
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