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
            
        // stage('Download Repository') {
        //    steps {
        //       // Download code from a GitHub repository
        //       // branch: the branch that you want to build
        //       // credentialsId: the ID of the credentials for your GitLab repo that is being managed by Jenkins
        //       // url: url to your repo
        //       git branch: 'master', credentialsId: 'd8d9e999-e44a-4924-950f-f767f4ebbcdd', url: 'https://github.com/pGuillergan/task-bunny-spring.git'
              
        //    }

        // }
      
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

    //       stage('Install maven dependencies'){
    //         steps{
    //             //clean install maven
    //             sh 'mvn clean install'
    //         }

    //     }

    //     stage ('Run Spring App') {
    //         steps {
    //             sh 'nohup mvn spring-boot:run &' 
    //         }
    //     }
    
    //  }
}
}