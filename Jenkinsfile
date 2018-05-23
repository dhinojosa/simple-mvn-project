pipeline {
  agent any
  tools {
        maven 'apache-maven-3.5.2'
        jdk 'JDK_1.8.0_162'
  }
  stages {
    stage('Unit') {
      steps {
        echo 'Unit'
        echo 'Running Maven Version'
        sh 'mvn -v'
        script {
           if (isUnix()) {
              sh 'mvn install'
           } else {
              bat 'mvn install'
           }
        }
      }
      post {
        always {
          archive "**/target/**/*.jar"
          junit 'simple-maven-app/target/surefire-reports/*.xml'
        }
      }
    }
    stage('Integration') {
      steps {
        echo 'Integration'
      }
    }
  }
}
