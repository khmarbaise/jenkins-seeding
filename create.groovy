import hudson.scm.*
import hudson.model.*
import org.tmatesoft.svn.core.*
import org.tmatesoft.svn.core.io.*
import java.util.*


def existingMavenInstallations = [ "Maven 2.2.1", "Maven 3.0.5", "Maven 3.1.1", "Maven 3.2.1", "Maven 3.2.2" ]
def existingJDKInstallations = [ "JDK-1.5-u22", "JDK-1.6-u45", "JDK-1.7-u40", "JDK-1.8-u5" ]

def existingApacheMavenPlugins = [
  "maven-acr-plugin",
  "maven-ant-plugin",
  "maven-antrun-plugin",
  "maven-assembly-plugin",
  "maven-changelog-plugin",
  "maven-changes-plugin",
  "maven-checkstyle-plugin",
  "maven-clean-plugin",
  "maven-compiler-plugin",
  "maven-dependency-plugin",
  "maven-deploy-plugin",
  "maven-doap-plugin",
  "maven-docck-plugin",
  "maven-ear-plugin",
  "maven-eclipse-plugin",
  "maven-ejb-plugin",
  "maven-gpg-plugin",
  "maven-help-plugin",
  "maven-install-plugin",
  "maven-invoker-plugin",
  "maven-jar-plugin",
  "maven-jarsigner-plugin",
  "maven-javadoc-plugin",
  "maven-linkcheck-plugin",
  "maven-patch-plugin",
  "maven-pdf-plugin",
  "maven-plugins",
  "maven-pmd-plugin",
  "maven-project-info-reports-plugin",
  "maven-rar-plugin",
  "maven-reactor-plugin",
  "maven-remote-resources-plugin",
  "maven-repository-plugin",
  "maven-resources-plugin",
  "maven-scm-publish-plugin",
  "maven-shade-plugin",
  "maven-site-plugin",
  "maven-source-plugin",
  "maven-stage-plugin",
  "maven-toolchains-plugin",
  "maven-verifier-plugin",
  "maven-war-plugin",
]

def svn_apache_plugin = 'http://svn.apache.org/repos/asf/maven/plugins/trunk'

existingApacheMavenPlugins.each {
  plugin ->
    println " Plugin: " + plugin
    job {
      name ('DSL-' + plugin)
      disabled(true)

      jdk("JDK-1.7-u40")
      scm {
          svn (svn_apache_plugin + '/' + plugin + '/', '.')
      }
      triggers {
          scm('H/15 * * * *')
      }
      steps {
        existingMavenInstallations.each {
          installation ->
            println " Maven: '" + installation + "'"
            maven {
                mavenInstallation(installation)
                goals("-B -Prun-its clean verify")
                localRepository(LocalToWorkspace)
            }
        }
      }

    }
}

