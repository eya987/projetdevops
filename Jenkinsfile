pipeline {
   environment {
        registry = "eya987/devopsproject"
        registryCredential = 'dockerHub'
        dockerImage = ''
    }
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
  -Dsonar.projectKey=sonarqube \
  -Dsonar.host.url=http://192.168.1.140:9000 \
  -Dsonar.login=e1aa46ab5f5d36c15b25dd6909f3c3ada9fe1f2a"
      	}
    }
        stage('Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
            stage('Test mvn') {
            steps {
              sh """ mvn -DskipTests clean package """ 
                sh """ mvn install """;
  
            }
        }
     stage('MVN PACKAGE') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
            stage('Building our image') {
            steps {
                script {
                    dockerImage = docker.build registry +":$BUILD_NUMBER"
                }
            }
        }
      stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }    
                stage('Cleaning up') {
            steps {
                echo "docker rmi $registry:$BUILD_NUMBER "
                sh "docker rmi $registry:$BUILD_NUMBER "
        }
    }

     stage('Start container') {
             steps {
                sh 'docker-compose up -d '
      }
        }
       }
      }