import sbt.Keys._
import sbt._

val appName = "enrolments-orchestrator-performance-tests"

scalaVersion := "2.11.11"

logLevel := sbt.Level.Info

resolvers ++= Seq(
  Resolver.bintrayRepo("hmrc", "releases"),
  Resolver.typesafeRepo("releases")
)

libraryDependencies ++= Seq(
  "io.gatling"              %  "gatling-test-framework"     % "2.2.5",
  "io.gatling.highcharts"   %  "gatling-charts-highcharts"  % "2.2.5",
  "com.typesafe"            %  "config"                     % "1.3.0",
  "com.github.tomakehurst"  %  "wiremock-jre8"              % "2.22.0",
  "uk.gov.hmrc"             %% "performance-test-runner"    % "3.5.0"
)

enablePlugins(GatlingPlugin)
