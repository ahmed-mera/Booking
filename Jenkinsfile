pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        git(url: 'https://github.com/ahmed-mera/Booking.git', branch: 'master')
      }
    }

    stage('Test') {
      steps {
        echo 'Starting test'
      }
    }

    stage('Run') {
      steps {
        sh 'cd $WORKSPACE/src; javac Server/Main.java'
      }
    }

  }
}