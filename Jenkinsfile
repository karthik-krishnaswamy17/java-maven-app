pipeline{
    agent any
    
    stages{
        stage("Copy all necessary files to Ansible"){
            steps {
                script{ 
                ssh-agent['ansible-key']{
                    sh "scp -o StrictHostKeyChecking=no playbooks/* ubuntu@18.207.184.245:/home/ubuntu/"
                }
                }
            }
        }
    }



}