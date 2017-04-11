name := "untitled"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.3"

libraryDependencies += "net.liftweb" %% "lift-json" % "2.6+"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.17"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.4.17"


libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "0.13",

  // Native libraries are not included by default. add this if you want them (as of 0.7)
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.13",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "0.13"
)


resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"