lazy val root = (project in file(".")).
settings(
  name := "banditAlgos",
  version := "0.0.1",
  scalaVersion := "2.11.7",
  mainClass := Some("EpsilonGreedy")
)
