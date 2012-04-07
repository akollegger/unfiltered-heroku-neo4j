import sbt._
object UnfilteredHerokuNeo4j extends Build
{
  lazy val root =
    Project("root", file(".")) dependsOn(dispatchLiftJson)
  lazy val dispatchLiftJson =
    uri("git://github.com/dispatch/dispatch-lift-json#0.1.1")
}

