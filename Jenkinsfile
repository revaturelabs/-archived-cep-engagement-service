pipeline {
    agent any
	
    environment{
        JENKINS_NODE_COOKIE = 'dontkillmeplease'
        PORT=8081
    } 
     stages {
        stage('Preparation') { // for display purposes
            steps {
              // clean the workspace
              cleanWs()
            }
        }
            
        stage('Download Repository') {
           steps {
              // credentialsId: the ID of the credentials for your GitLab repo that is being managed by Jenkins
              // url: url to your repo
              git branch: 'progress-dev', credentialsId: 'bf453e33-834d-41b7-bbd4-a0f1851f9d81', url: 'https://github.com/revaturelabs/cep-engagement-service.git'
           }
        }
      
         stage('Destroy Old Server') {
            steps {
                script {
                    try {
                        // kill any running instances
                        sh 'kill $(lsof -t -i:$PORT)'
                    } catch (all) {
                        // if it fails that should mean a server wasn't already running
                        echo 'No server was already running'
                    }
                }
            }
        }

          stage('Install maven dependencies'){
            steps{
                //clean install maven
                sh 'mvn clean install'
            }
        }

        stage ('Run Spring App') {
            steps {
                // sh 'java -jar target/cep '
                sh 'nohup mvn spring-boot:run &' 
            }
        }
     }
}
