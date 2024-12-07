pipeline {
    agent any
    environment {
        // Set environment variables if needed
        BROWSER = "${params.BROWSER ?: 'chrome'}" // Default to Chrome if not provided
        HEADLESS = "${params.HEADLESS ?: 'true'}" // Default to headless mode
    }
    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests (chrome, firefox, edge)')
        string(name: 'HEADLESS', defaultValue: 'true', description: 'Run tests in headless mode (true or false)')
    }
    stages {
        stage('Preparation') {
            steps {
                echo "Setting up environment for ${BROWSER} tests in headless mode: ${HEADLESS}"
                sh 'java -version'
                sh 'mvn -version'
            }
        }
        stage('Build') {
            steps {
                echo 'Building Maven project...'
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                echo "Running tests on ${BROWSER} in headless mode: ${HEADLESS}"
                sh "mvn test -Dbrowser=${BROWSER} -Dheadless=${HEADLESS}"
            }
        }
        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure report...'
                sh 'mvn allure:report'
            }
        }
        stage('Publish Allure Report') {
            steps {
                allure([
                    reportBuildPolicy: 'ALWAYS',
                    includeProperties: true,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
