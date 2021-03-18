name := "assignment"

version := "0.1"

scalaVersion := "2.12.6"


/*libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.10",
  "com.typesafe.akka" %% "akka-http" % "10.2.2",
  "org.json4s" %% "json4s-jackson" % "3.7.0-M7",
  "org.json4s" %% "json4s-native" % "3.7.0-M7",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.15")*/

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.15",
  "org.json4s" %% "json4s-jackson" % "3.7.0-M7",
  "org.json4s" %% "json4s-native" % "3.7.0-M7",
  /*"com.google.inject" % "guice" % "4.2.0",*/
)