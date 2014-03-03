// job (type: Maven) {

def existingMavenInstallations = [ "Maven 2.0.11", "Maven 2.2.1", "Maven 3.0.5", "Maven 3.1.0", "Maven 3.1.1" ]

/* def svn_root = 'http://svn.apache.org/repos/asf/maven/plugins/'

def modules() {
  ret = []

  def repository = new SubversionSCM.ModuleLocation(svn_root, '.').openRepository(template)
  for(entry in repository.getDir('trunk', SVNRepository.INVALID_REVISION, (SVNProperties) null, (Collection) null)) {
    ret.add(entry.name)
  }

  ret
}

def modules = modules()

modules.each {
  println "Module:" + it
}
*/
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
        existingMavenInstallations.each {
          println " Maven: '" + it + "'"
          maven {
              //The following could not be working at the moment based:
              //https://github.com/JavaPosseRoundup/job-dsl-plugin/blob/master/src/main/groovy/javaposse/jobdsl/dsl/helpers/StepHelper.groovy#L145
              //mavenName(it)
              mavenInstallation(it)
              goals("-B -Prun-its clean verify")
              localRepository(LocalToWorkspace)

          }
        }
    }
}
