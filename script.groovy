#!/bin/bash/env groovy
def buildJar(){
    echo "Building Jar...."
    sh 'mvn package'

}

def buildImage(){
        script{
                echo "Building Image"
                withCredentials([usernamePassword(credentialsId:'docker-hub-account',usernameVariable:'user',passwordVariable:'pass')]){
                sh 'docker build -t karthik0517/java-maven-app:${BUILD_NUMBER} .'
                sh "echo $pass |docker login --username $user --password-stdin"
                sh 'docker push karthik0517/java-maven-app:${BUILD_NUMBER}'
                }
              }
}

def deployApp(){
    echo "Deploying...groovy"
}

return this