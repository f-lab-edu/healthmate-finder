pipeline{
	agent any
	
	environment {
	    image = ''
	}
	
	tools{
	    gradle 'gradle5.0'
	}
	
	stages{
	    stage('git pull'){
	        steps{
	            checkout scm
	            echo 'git pull success'
	        }
	    }
	
	
	    stage('build'){
	        steps{
	            sh 'gradle clean build --exclude-task test'
	            echo 'build success'
	        }
	    }
	
	    stage('Test') {
	        steps {
	            sh 'gradle test'
	            echo 'Test Success!'
	        }
	    }
	
	    stage('Build Docker Image') {
	        steps {
	            script {
	                image = docker.build('rkdl0829/helparty')
	            }
	        }
	    }
	
	    stage('Push Docker Image') {
	        steps {
	            script{
	                docker.withRegistry('<https://registry.hub.docker.com>', 'docker-hub') {
	                    image.push('latest')
	                }
	            }
	        }
	    }
	
	    stage('Remove Docker Image') {
	        steps {
	            sh 'docker rmi rkdl0829/helparty'
	            sh 'docker rmi registry.hub.docker.com/rkdl0829/helparty:latest'
	        }
	    }
	
	    stage('Deploy') {
	        steps([$class: 'BapSshPromotionPublisherPlugin']) {
	            sshPublisher(
	                continueOnError: false, failOnError: true,
	                publishers: [
	                    sshPublisherDesc(
	                        configName: "was-1",
	                        verbose: true,
	                        transfers: [
	                            sshTransfer(
	                                sourceFiles: "",
	                                removePrefix: "",
	                                remoteDirectory: "",
	                                execCommand: "sh ~/scripts/deploy-docker.sh"
	                            )
	                        ]
	                    )
	                ]
	            )
	        }
	    }
	
	}
}
