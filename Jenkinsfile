pipeline{
    agent any
     environment {
        ANSIBLE_SERVER = "44.199.201.2"
    }

    
    stages{
        stage("Copy all necessary files to Ansible"){
            
            steps {
                
                script{ 
                       
                    sshagent(['ansible-key']){
                    sh "scp -o StrictHostKeyChecking=no playbooks/* ubuntu@$ANSIBLE_SERVER:/home/ubuntu/"
                       
                       withCredentials([sshUserPrivateKey(credentialsId: 'ansible-key', keyFileVariable:'keyfile',usernameVariable: 'user' )]){
                        sh  'scp -i $keyfile $keyfile  ubuntu@$ANSIBLE_SERVER:/home/ubuntu/.ssh/id_rsa'
                        // sh ' ssh -i $keyfile ubuntu@$ANSIBLE_SERVER uname -a'
                        }

                    }
                }
            } 
        }
        stage ("Execute Playbook"){
            steps{
                script{
                    echo "calling ansible playbook to configure ec2 instances"
                    def remote = [:]
                    remote.name = "ansible-server"
                    remote.host = ANSIBLE_SERVER
                    remote.allowAnyHosts = true

                    withCredentials([sshUserPrivateKey(credentialsId: 'ansible-key', keyFileVariable: 'keyfile', usernameVariable: 'user')]){
                        remote.user = user
                        remote.identityFile = keyfile
                        sshCommand remote: remote, command: "ansible-playbook main.yaml"
                    }
                }
            }
        }

}
}
