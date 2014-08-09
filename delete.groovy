import hudson.scm.*
import hudson.model.*
import org.tmatesoft.svn.core.*
import org.tmatesoft.svn.core.io.*
import java.util.*

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

def existingMojoCodehausPlugins = [
  "animal-sniffer",
  "antlr-maven-plugin",
  "appassembler",
  "apt-maven-plugin",
  "aspectj-maven-plugin",
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
]

existingMojoCodehausPlugins.each {
  plugin ->
    dsl { 
      removeAction('DELETE')
      job {
        name ('DSL-' + plugin)
      }
    }
}

existingApacheMavenPlugins.each {
  plugin ->
    dsl {
      removeAction('DELETE')
      job {
        name ('DSL-' + plugin)
      }
    }
}
