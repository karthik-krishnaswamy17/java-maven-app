#!/usr/bin/env groovy
@Library('shared-lib-jenkin')
def gv

pipeline{
agent any
tools{
    maven 'Maven'
    jdk 'Java'
}

stages{

    stage("init"){
        steps{
            script{
                sh 'mvn build-helper:parse-version \
                versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                versions:commit'


                gv= load "script.groovy"    
            }
            
        }
    }


    stage("build jar"){
        steps{
            
            script{
                echo "+++++++++++++++++++++++"
                buildJar()
            }
        }
    }


    stage("build and push image"){
        steps{
            script{
                $IMAGE_NAME=getVersion()
                echo "Image name is: ${IMAGE_NAME}"
                buildImage "karthik0517/java-maven-app:$IMAGE_NAME"
                dockerLogin()
                dockerPush 'karthik0517/java-maven-app:$IMAGE_NAME'
            }
            
        }
    }

    stage("deploy"){
        steps{
            script{
                gv.deployApp()
            }
            
        }
    }


}

}
