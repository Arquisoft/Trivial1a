name := "Trivial1a"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  javaJdbc,
  javaEbean,
  cache,
  "com.google.inject" % "guice" % "3.0" % "test",
  "info.cukes" % "cucumber-guice" % "1.1.5" % "test",
  "info.cukes" % "cucumber-java" % "1.1.5" % "test",
  "info.cukes" % "cucumber-junit" % "1.1.5" % "test"
)

javaOptions in Test += "-Dconfig.file=conf/test.conf"

unmanagedResourceDirectories in Test <+= baseDirectory( _ / "features" )
