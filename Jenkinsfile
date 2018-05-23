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
        echo 'Running Maven Version'
        sh 'mvn -v'
        if (isUnix()) {
           sh '${M2_HOME}/bin/mvn -pl install'
        } else {
           bat '${M2_HOME}\\bin\\mvn install'
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
