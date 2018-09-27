pipeline {
node('Master') {
  def mvnHome
  stage('Preparation') { // for display purposes
       // Get some code from a GitHub repository
       git 'https://github.com/dhinojosa/simple-mvn-project.git'
       // Get the Maven tool.
       // ** NOTE: This 'apache-maven-3.5.4' Maven tool must be configured
       // **       in the global configuration.
       mvnHome = tool 'apache-maven-3.5.4'
  }
  stage('Build') {
       // Run the maven build
       if (isUnix()) {
          sh "'${mvnHome}/bin/mvn' clean package"
       } else {
          bat(/"${mvnHome}\bin\mvn" clean package/)
       }
  }
  stage('Results') {
       junit 'simple-maven-common/target/surefire-reports/TEST-*.xml'
       archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
  }
}
node('Linux') {
   stage("Integration Test") {
      javaHome = tool 'java-1.8.0_162'
      sh "'${javaHome}/bin/java' -cp 'simple-maven-common/target/*:simple-maven-app-integration-test/target/*' org.junit.runner.JUnitCore com.evolutionnext.services.AlbumServiceBeanIntegrationIntTest"
   }
}
}