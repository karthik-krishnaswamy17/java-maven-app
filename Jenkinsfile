#!/usr/bin/env groovy
@Library('shared-jenkin-lib')
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

            gv= load "script.groovy"    
            }
            
        }
    }


    stage("build jar"){
        steps{
            
            script{
                
                buildJar 'karthik0517/java-maven-app:${BRANCH_NAME}_${BUILD_NUMBER}'
            }
        }
    }


    stage("build image"){
        steps{
            script{
                buildImage()
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