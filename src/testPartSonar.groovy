pipeline {
    agent any
    stages {
        stage 'checkout codes', {
            steps {
                git branch: 'part', url: 'https://github.com/23335049/cache-timeout.git'
            }
        }
        stage 'SonarQube analysis changes from master', {
            steps {
                withSonarQubeEnv('SonarQube') {
                    echo(sh (returnStdout: true, script: "git diff HEAD master --name-only | tr '\n' ',' "))
                    sh "mvn -f pom.xml clean compile sonar:sonar " +
                            "-Dsonar.sources=${sh returnStdout: true, script: "git diff HEAD master --name-only | tr '\n' ',' "}"
                }
            }
        }
    }
}