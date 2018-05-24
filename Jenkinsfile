pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool(name: 'apache-maven-3.5.2', type: 'maven')
        script {
          if (isUnix()) {
            sh "mvn package"
          } else {
            bat "mvn package"
          }
        }

      }
      post {
        always {
          archiveArtifacts(artifacts: '**/target/**/*.jar', fingerprint: true, onlyIfSuccessful: true)
          junit 'simple-maven-common/target/surefire-reports/*.xml'
        }
      }
    }
    stage('Integration') {
      steps {
        sh 'echo "Integration"'
      }
      post {
         always {
            mail(body: 'Current Build status is ${env.BUILD_STATUS}', subject: 'Build ${env.BUILD_ID}', to: 'dhinojosa@evolutionnext.com', mimeType: 'text/html')
         }
      }
    }
  }
}