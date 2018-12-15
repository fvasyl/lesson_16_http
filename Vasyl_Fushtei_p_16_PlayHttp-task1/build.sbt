name := "PlayHttp"

version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(guice)
libraryDependencies += "com.typesafe.play" %% "play" % "2.5.12"


