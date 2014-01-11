job {
    name 'DSL-Tutorial-1-Test-Artifactor-maven-plugin'
    scm {
        git('git@github.com:khmarbaise/artifactor-maven-plugin.git')
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        maven('-B -Prun-its clean verify')
    }
}
