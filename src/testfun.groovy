pipeline {
    agent any
    par
    stages {
        stage('checkout') {
            steps {
                git branch:'deploy', url: 'https://github.com/23335049/cache-timeout.git'
            }
        }
//        stage('test_mail'){
//            environment {
//                LAST_COMMIT_EMAIL = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'")
//            }
//            steps {
//                emailext to: LAST_COMMIT_EMAIL,
//                         subject: 'test_mail',
//                         body: 'a test mail.'
//            }
//        }
        stage("test_env") {
            environment {
                LAST_COMMIT_EMAIL = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'")
            }
            steps {
                script {
                    env.properties.each {echo it}
                }
            }
        }
    }
}