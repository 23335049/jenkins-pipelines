pipeline {
    stages {
        stage('test_mail'){
            mail(to: '23335049@qq.com', subject: 'test_mail', body: 'a test mail.')
        }
    }
}