name                      := "http4s-rest-api-example"
ThisBuild / version       := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion  := "2.13.14"

val circeVersion              = "0.14.9"
val circeGenericExtrasVersion = "0.14.4"
val http4sVersion             = "0.23.27"
val scalaTestVersion          = "3.2.19"
val scalaTestMockitoVersion   = "3.2.19.0"
val wireMockVersion           = "3.9.1"
val wiremockVersion           = "3.9.1"

ThisBuild / libraryDependencies ++= Seq(
  "org.http4s"             %% "http4s-circe"             % http4sVersion,
  "org.http4s"             %% "http4s-dsl"               % http4sVersion,
  "org.http4s"             %% "http4s-ember-client"      % http4sVersion,
  "io.circe"               %% "circe-core"               % circeVersion,
  "io.circe"               %% "circe-generic"            % circeVersion,
  "io.circe"               %% "circe-generic-extras"     % circeGenericExtrasVersion,
  "org.scalatest"          %% "scalatest"                % scalaTestVersion % "test",
  "org.scalatestplus"      %% "mockito-5-12"             % scalaTestMockitoVersion % "test",
  "org.wiremock"           %  "wiremock"                 % wireMockVersion  % "test",
)