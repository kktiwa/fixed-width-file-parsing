ThisBuild / name := "Fixed Width File Parser"
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.12"
ThisBuild / scalaBinaryVersion := "2.12"

lazy val root = (project in file("fixed-width-file-parsing"))
  .settings(
    name := "fixed-width-file-parsing"
  )

lazy val circeVersion = "0.14.5"
lazy val scalaTestVersion = "3.2.15"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion) ++ Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint:_",
  "-Ywarn-dead-code",
  "-Ywarn-unused-import",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
)

Compile / console / scalacOptions --= Seq("-Ywarn-unused", "-Ywarn-unused-import")
Compile / scalacOptions --= Seq("-Xfatal-warnings", "-Ywarn-unused-import")
