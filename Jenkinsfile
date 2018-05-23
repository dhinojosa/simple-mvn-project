pipeline {
  agent any
  tools {
        maven 'apache-maven-3.5.2'
        jdk 'JDK_10.0.1'
  }
  stages {
    stage('Unit') {
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