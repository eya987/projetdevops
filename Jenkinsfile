pipeline {
   environment {
        registry = "rihemabess/devopsproject"
        registryCredential = 'dockerHub'
        dockerImage = ''
    }
   agent any
   stages {
    stage('Git Checkout') {
      steps {
        echo 'pulling...';
         git branch:'rihem',
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
  -Dsonar.host.url=http://192.168.0.15:9000 \
  -Dsonar.login=23a5cbbe6e188026b31e6dc76d09b153ad0a2419"
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