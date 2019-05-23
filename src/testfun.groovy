pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
                git branch:'deploy', url: 'https://github.com/23335049/cache-timeout.git'
                environment {
                    LAST_COMMIT_EMAIL = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'")
                }
                echo LAST_COMMIT_EMAIL
                echo env.LAST_COMMIT_EMAIL
            }
        }
        stage('test_mail'){
            steps {
                emailext to: LAST_COMMIT_EMAIL,
                         subject: 'test_mail',
                         body: 'a test mail.'
            }
        }
    }
}