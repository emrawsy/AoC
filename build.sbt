ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "org.scalactic" %% "scalactic" % "3.2.14"
)
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "AdventOfCode"
  )
