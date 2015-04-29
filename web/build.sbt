name := "Trivial1a"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  "info.cukes" % "cucumber-junit" % "1.2.2",
  "info.cukes" % "cucumber-java" % "1.2.2",
  "info.cukes" % "cucumber-guice" % "1.2.2",
  "info.cukes" % "cucumber-scala_2.10" % "1.2.2",
  "com.google.inject" % "guice" % "3.0" 
)


javaOptions in Test += "-Dconfig.file=conf/test.conf"
