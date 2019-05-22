pipeline {
    agent any
    stages {
        stage('test_mail'){
            steps {
                mail(to: '23335049@qq.com', subject: 'test_mail', body: 'a test mail.')
            }
        }
    }
}