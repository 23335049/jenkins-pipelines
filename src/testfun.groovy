pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
                git branch:'deploy', url: 'https://github.com/23335049/cache-timeout.git'
                script {
                    def lastCommitEmail = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'")
                    echo lastCommitEmail
                    properties([parameters([string(name: 'lastCommitEmail', value: lastCommitEmail)])])
                }
            }
        }
        stage('test_mail'){
            steps {
                emailext to: "$params.lastCommitEmail",
                         subject: 'test_mail',
                         body: 'a test mail.'
            }
        }
    }
}