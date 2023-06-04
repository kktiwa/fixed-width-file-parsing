ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / scalaBinaryVersion := "2.13"

lazy val root = (project in file("."))
  .settings(
    name := "fixed-width-file-parsing",
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