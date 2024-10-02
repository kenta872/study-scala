import sbt.Keys.{libraryDependencies, scalacOptions}

lazy val commonSettings = Seq(
    scalaVersion := "2.13.15",
    libraryDependencies += guice,
    // DB
    libraryDependencies ++= Seq(
        "mysql" % "mysql-connector-java" % "8.0.33",
        "com.typesafe.play" %% "play-slick" % "5.3.0",
        "com.typesafe.play" %% "play-slick-evolutions" % "5.3.0",
        "com.typesafe.slick" %% "slick" % "3.5.2",
        "com.typesafe.slick" %% "slick-hikaricp" % "3.5.2"
    ),
    // Test
    libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play-test" % "2.9.4" % Test,
        "org.mockito" %% "mockito-scala" % "1.17.37" % Test,
        "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
    )
)
lazy val root = (project in file("."))
    .aggregate(studyScalaApp)

lazy val studyScalaApp = (project in file("study-scala-app"))
    .enablePlugins(PlayScala)
    .dependsOn(studyScalaCommon)
    .settings(
        name := "study-scala-app",
        version:= "1.0-SNAPSHOT",
        Compile / unmanagedSourceDirectories += baseDirectory.value / "app",
        Test / unmanagedSourceDirectories += baseDirectory.value / "test",
        scalacOptions += "-Ywarn-unused",
        ThisBuild / semanticdbEnabled := true, // semanticdb を有効化
        ThisBuild / semanticdbVersion := "4.10.1", // Scala 2.13 と互換性のあるバージョン
        ThisBuild / scalafixScalaBinaryVersion := "2.13",
    )
    .settings(commonSettings)

lazy val studyScalaCommon = (project in file("study-scala-common"))
    .settings(
        name := "study-scala-common",
        version:= "1.0-SNAPSHOT",
        Compile / unmanagedSourceDirectories += baseDirectory.value / "app",
        Test / unmanagedSourceDirectories += baseDirectory.value / "test",
        scalacOptions += "-Ywarn-unused",
        ThisBuild / semanticdbEnabled := true, // semanticdb を有効化
        ThisBuild / semanticdbVersion := "4.10.1", // Scala 2.13 と互換性のあるバージョン
        ThisBuild / scalafixScalaBinaryVersion := "2.13",
    )
    .settings(commonSettings)
