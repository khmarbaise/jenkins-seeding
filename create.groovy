// job (type: Maven) {
job {

    name 'DSL-Tutorial-1-Test-Artifactor-maven-plugin'

    //
//    jdk("JDK-1.5-u22")
//    jdk("JDK-1.6-u45")
    jdk("JDK-1.7-u40")

//    mavenInstallation("Maven 3.1.1")
//    localRepository(LocalToWorkspace)
//    goals("-B -Prun-its clean test")

    scm {
        git('git@github.com:khmarbaise/artifactor-maven-plugin.git')
    }

    triggers {
        scm('H/15 * * * *')
    }

    steps {
        maven {
            mavenVersion = 'Maven 3.0.5'
            goals("-B -Prun-its clean verify")
        }
        maven {
            mavenVersion = 'Maven 3.1.1'
            goals("-B -Prun-its clean verify")
        }
    }
}
