lazy val root =
  (project in file(".")).
    enablePlugins(PlayScala).
    settings(
      name := "play-demo",
      organization := "com.spotright",
      version := "1.0-SNAPSHOT",

      scalaVersion := "2.11.8",

      libraryDependencies += filters,
      libraryDependencies ++= Seq(
        "org.scalaz" %% "scalaz-core" % "7.2.6",
        "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
      )
    )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.spotright.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.spotright.binders._"
