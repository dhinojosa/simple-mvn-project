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
      post {
         always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            emailext body: '''Here is the result email trying different variants

            env.BUILD_ID = ${env.BUILD_ID}
            BUILD_ID = ${BUILD_ID}
            currentBuild.result = ${currentBuild.result}
            result = ${result}''',
                     mimeType: 'text/html',
                     recipientProviders: [culprits()],
                     replyTo: 'dhinojosa@evolutionnext.com',
                     subject: '${BUILD_ID} is ${currentBuild.result}',
                     to: 'dhinojosa@evolutionnext.com'
         }
      }
    }
  }
}