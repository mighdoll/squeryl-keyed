import sbt._

class LogServerProject(info: ProjectInfo) extends DefaultProject(info) {
  val squeryl = "org.squeryl" % "squeryl_2.8.0" % "0.9.4-RC2"
  val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.10"
}
