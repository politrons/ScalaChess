name := "ScalaChess"

version := "0.1"

scalaVersion := "2.12.8"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)