library identifier: 'custom-lib@master', retriever: modernSCM(
        [$class: 'GitSCMSource',
         remote: 'git@github.com:23335049/jenkins-utils.git'])
pipeline {
    agent any
    stages{
        stages('test') {
            steps {
                script {
                    log.info "woqu.this"
                    log.warning "woqu.this"
                }
            }
        }
    }
}