import javax.naming.Name

pipeline {
    agent any
    tools {
        maven 'maven-3.6.1'
        git 'Default'
    }
    parameters {
        string name: branch, defaultValue: 'master', description: '扫描的branch名称'
    }
    environment {
//        AN_ACCESS_KEY = credentials('my-prefined-secret-text')
        SONAR_PROJECT_TYPE = 'part_sonar'
        GIT_URL = 'https://github.com/23335049/part_sonar.git'
        BASE_BRANCH = 'master'
    }
    stages {
        stage 'checkout codes', {
            steps {
                git branch: params.branch, url: GIT_URL
            }
        }
        stage "SonarQube analysis changes from ${BASE_BRANCH}", {
            steps {
                withSonarQubeEnv('SonarQube') {
                    echo(sh (returnStdout: true, script: "git diff HEAD origin/${BASE_BRANCH} --name-only | tr '\n' ',' "))
                    sh "mvn -f pom.xml clean compile sonar:sonar " +
                            "-Dsonar.projectKey=${SONAR_PROJECT_TYPE}-${params.branch} " +
                            "-Dsonar.projectName=${SONAR_PROJECT_TYPE}-${params.branch} " +
                            "-Dsonar.sources=${sh returnStdout: true, script: "git diff HEAD origin/${BASE_BRANCH} --name-only | tr '\n' ',' "}"
                }
            }
        }
    }
}