job {
    name 'DSL-Tutorial-1-Test-Artifactor-maven-plugin'

    scm {
        git('git@github.com:khmarbaise/artifactor-maven-plugin.git')
    }

    triggers {
        scm('H/15 * * * *')
    }

    steps {
        maven {
            version = "Maven 3.1.1"
        }

        mavenInstallation("Maven 3.1.1")
        maven('-B -Prun-its clean verify')
    }
}
