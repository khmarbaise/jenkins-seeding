// job (type: Maven) {

def mavenVersions = [ "Maven 2.0.11", "Maven 2.2.1", "Maven 3.0.5", "Maven 3.1.0", "Maven 3.1.1" ]

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
        mavenVersions.each {
          println " Maven: '" + it + "'"
          maven {
              mavenInstallation(it)
              goals("-B -Prun-its clean verify")
              localRepository(LocalToWorkspace)

          }
        }
    }
}
