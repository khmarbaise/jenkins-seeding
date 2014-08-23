import hudson.scm.*
import hudson.model.*
import org.tmatesoft.svn.core.*
import org.tmatesoft.svn.core.io.*
import java.util.*

def existingMavenInstallations = [ 
  "Maven 2.2.1", 
  "Maven 3.0.5", 
  "Maven 3.1.1", 
  "Maven 3.2.1", 
  "Maven 3.2.2", 
  "Maven 3.2.3" 
]

def maven2 = existingMavenInstallations[0]

def existingJDKInstallations = [ "JDK-1.5-u22", "JDK-1.6-u45", "JDK-1.7-u40", "JDK-1.8-u5" ]
def maven32JDK = existingJDKInstallations[1..3]


def existingMojoCodehausPlugins = [
  "animal-sniffer",
  "antlr-maven-plugin",
  "appassembler",
  "apt-maven-plugin",
  "aspectj-maven-plugin",

/*
  "axistools-maven-plugin",
  "batik-maven-plugin",
  "build-helper-maven-plugin",
  "buildnumber-maven-plugin",
  "cassandra-maven-plugin",
  "castor-maven-plugin",
  "chronos",
  "clirr-maven-plugin",
  "cobertura-maven-plugin",
  "codenarc-maven-plugin",
  "commons-attributes-maven-plugin",
  "dashboard-maven-plugin",
  "dbunit-maven-plugin",
  "dbupgrade",
  "dependency-maven-plugin",
  "ditaot-maven-plugin",
  "docbook-maven-plugin",
  "emma-maven-plugin",
  "enchanter",
  "exec-maven-plugin",
  "extra-enforcer-rules",
  "findbugs-maven-plugin",
  "fitnesse-maven-plugin",
  "gwt-maven-plugin",
  "hibernate2-maven-plugin",
  "hibernate3-maven-plugin",
  "ianal-maven-plugin",
  "ideauidesigner-maven-plugin",
  "idlj-maven-plugin",
  "jalopy-maven-plugin",
  "jasperreports-maven-plugin",
  "javacc-maven-plugin",
  "javancss-maven-plugin",
  "javascript-maven-tools",
  "jaxb2-maven-plugin",
  "jboss-packaging-maven-plugin",
  "jdepend-maven-plugin",
  "jdiff-maven-plugin",
  "jpox-maven-plugin",
  "jruby-maven-plugin",
  "jruby-stdlib",
  "js-import-maven-plugin",
  "jslint-maven-plugin",
  "jspc",
  "keytool-maven-plugin",
  "keytool",
  "l10n-maven-plugin",
  "latex-maven",
  "lesscss-maven-plugin",
  "license-maven-plugin",
  "maven-extensions",
  "maven-native",
  "mojo-archetypes",
  "mojo-parent",
  "mrm",
  "native2ascii-maven-plugin",
  "nbm-maven",
  "nsis-maven-plugin",
  "openjpa-maven-plugin",
  "osxappbundle-maven-plugin",
  "ounce-maven-plugin",
  "pde-maven-plugin",
  "plugin-support",
  "properties-maven-plugin",
  "retrotranslator-maven-plugin",
  "rmic-maven-plugin",
  "rpm-maven-plugin",
  "sablecc-maven-plugin",
  "scmchangelog-maven-plugin",
  "selenium-maven-plugin",
  "ship-maven-plugin",
  "shitty-maven-plugin",
  "siteskinner-maven-plugin",
  "smc-maven-plugin",
  "solaris",
  "sonar-maven-plugin",
  "springws-maven-plugin",
  "sql-maven-plugin",
  "sqlj-maven-plugin",
  "taglist-maven-plugin",
  "templating-maven-plugin",
  "tidy-maven-plugin",
  "truezip",
  "versions-maven-plugin",
  "vfs",
  "wagon-maven-plugin",
  "was6-maven-plugin",
  "weblogic-maven-plugin",
  "webminifier-maven-plugin",
  "webstart",
  "webtest-maven-plugin",
  "xdoclet-maven-plugin",
  "xml-maven-plugin",
  "xmlbeans-maven-plugin",

*/
]

def svn_mojo_plugin = 'https://svn.codehaus.org/mojo/trunk/mojo'


def folderName = "mojo-maven-plugins"

folder {
  name folderName
  displayName ('Mojo Codehaus Maven Plugins')
  description ('''<div><img src="http://mojo.codehaus.org/images/codehaus-small.png"/>
<h1>Mojo Codehaus</h1>
</div>''')

}

existingMavenInstallations.each {
  mavenInst ->
    println " Maven Version:" + mavenInst
    def mavenJobName = mavenInst.replaceAll(' ', '-')
    println "   Job:" + mavenJobName
    existingMojoCodehausPlugins.each {
      plugin ->
        println " Matrix Plugin: " + plugin + " MavenVersion:" + mavenInst
        jobName = 'Matrix-' + mavenJobName + '-' + plugin
        job ( type: Matrix) {
          name (folderName + "/" + jobName)
          disabled(false)
          def jdks = existingJDKInstallations
          if (mavenInst >= "Maven 3.2.1") {
            println "Greater Maven 3.2"
            jdks = maven32JDK
          }
          axes {
            jdk (jdks)
          }
          scm {
              svn (svn_mojo_plugin + '/' + plugin + '/', '.')
          }
          wrappers {
            timestamps ()
          }

          steps {
            maven {
                mavenInstallation(mavenInst)
                goals("-V -B -U -fae -Prun-its clean verify")
                localRepository(LocalToWorkspace)
            }
          }
          
        }
    }
}

existingMavenInstallations.each {
  mavenInst ->
    def regexMaven = mavenInst
      .replaceAll(' ', '-')
      .replaceAll('\\.', '\\\\.')
      .replaceAll('-', '\\-')

    view {
      name (folderName + "/" + mavenInst)
      columns {
        buildButton()
        lastBuildConsole() 
        lastDuration()
        lastFailure()
        lastSuccess()
        name()
        status()
        weather()
      }
      jobs {
        regex ("^Matrix-" + regexMaven + "-.*")
      }
    }

}
