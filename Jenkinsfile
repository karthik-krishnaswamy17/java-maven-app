pipeline{
    agent any
    
    stages{
        stage("Copy all necessary files to Ansible"){
            
            steps {
                
                script{ 
                       
                    sshagent(['ansible-key']){
                    sh "scp -o StrictHostKeyChecking=no playbooks/* ubuntu@18.207.184.245:/home/ubuntu/"
                       withCredentials([sshUserPrivateKey(credentialsId:ansible-key,keyFileVariable:'keyfile',usernameVariable: 'user' )]){
                        sh " scp ${keyfile} ubuntu@18.207.184.245:/home/ubuntu/.ssh/id_rsa"
                        }

                    }
                }
            }
        }
    }



}
