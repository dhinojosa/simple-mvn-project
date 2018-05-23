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

        archiveArtifacts(artifacts: '**/target/**/*.jar', fingerprint: true, onlyIfSuccessful: true)
        junit '**/target/surefire-reports/*.xml'
      }
    }
    stage('Integration') {
      steps {
        sh 'echo "Integration"'
        mail bcc: '', body: 'Current Build status is ${BUILD_STATUS}', cc: '', from: '', replyTo: '', subject: 'Build ${BUILD_ID}', to: 'dhinojosa@evolutionnext.com'
      }
    }
  }
}