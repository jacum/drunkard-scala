import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "nl.pragmasoft"
ThisBuild / organizationName := "drunkard"

lazy val root = (project in file("."))
  .settings(
    name := "Drunkard Card Game",
    libraryDependencies ++= List(parallels, scalaTest % Test)
  )
