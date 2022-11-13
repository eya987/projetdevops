pipeline {
   agent any
   stages {
    stage('Git Checkout') {
      steps {
        echo 'pulling...';
         git branch:'eya',
         url : 'https://github.com/eya987/projetdevops';
         }
        }
      stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }
       
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }
    stage("MVN SonarQube") {
      
       		steps {
        	  sh "mvn sonar:sonar \
  				-Dsonar.projectKey=socarqube \
  				-Dsonar.host.url=http://192.168.1.173:9000 \
  				-Dsonar.login=6e96b26c7adf6d429cc30258cf59c6aa8e33b666"
      	}
    }
            stage('Test mvn') {
            steps {
              sh """ mvn -DskipTests clean package """ 
                sh """ mvn install """;
  
            }
        }
  
       }
      }