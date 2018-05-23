pipeline {
  agent any
  stages {
    stage('Unit') {
      steps {
        echo 'Unit'
        echo 'Running Maven Version'
        sh 'mvn -v'
        script {
          if (isUnix()) {
            sh 'mvn package'
          } else {
            bat 'mvn package'
          }
        }

        archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
      }
      post {
        always {
          archiveArtifacts(artifacts: '**/target/**/*.jar', fingerprint: true)
          junit 'simple-maven-common/target/surefire-reports/*.xml'

        }

      }
    }
    stage('Integration') {
      steps {
        echo 'Integration'
      }
    }
  }
  tools {
    maven 'apache-maven-3.5.2'
    jdk 'JDK_1.8.0_162'
  }
}