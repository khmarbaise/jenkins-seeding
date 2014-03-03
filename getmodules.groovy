import hudson.scm.*
import hudson.model.*
import org.tmatesoft.svn.core.*
import org.tmatesoft.svn.core.io.*

def svn_root = 'http://svn.apache.org/repos/asf/maven/plugins/'

def modules() {
  ret = []

  def x = new org.tmatesoft.svn.core.wc.SVNLogClient(null, null)
//  def m = new hudson.scm.SubversionSCM(); 
//  def repository = new SubversionSCM.ModuleLocation(svn_root, '.').openRepository()
/*  for(entry in repository.getDir('trunk', SVNRepository.INVALID_REVISION, (SVNProperties) null, (Collection) null)) {
    ret.add(entry.name)
  }
*/
  ret
}

def modules = modules()

modules.each {
  println "Module:" + it
}

