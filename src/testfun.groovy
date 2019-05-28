pipeline {
    agent none
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
//        stage("test_params") {
//            environment {
//                LAST_COMMIT_EMAIL = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'")
//            }
//        }
    }
    post {
        always {
            emailext to: sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%ae'"),
                    subject: "job_name: ${JOB_NAME} build_id: ${BUILD_ID}",
                    body: "build url :${BUILD_URL}"
        }
    }
}
