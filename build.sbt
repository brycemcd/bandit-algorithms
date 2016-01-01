lazy val root = (project in file(".")).
settings(
  name := "banditAlgos",
  organization := "com.github.brycemcd",
  version := "0.0.1",
  scalaVersion := "2.11.7",
  mainClass in (Compile, run) := Some("com.github.brycemcd.bandit_algorithms.Main")
)
