import sbt.Keys.scalacOptions

name := """study-scala"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
        scalacOptions += "-Ywarn-unused",
        ThisBuild / semanticdbEnabled := true, // semanticdb を有効化
        ThisBuild / semanticdbVersion := "4.10.1", // Scala 2.13 と互換性のあるバージョン
        ThisBuild / scalafixScalaBinaryVersion := "2.13",
    )

scalaVersion := "2.13.15"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
