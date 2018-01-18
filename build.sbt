resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4") //Compiler plugin for making type lambdas (type projections) easier to write


val circeVersion = "0.7.0"

val cats = "org.typelevel" %% "cats-core" % "1.0.1"
val shapeless = "com.chuusai" %% "shapeless" % "2.3.2"
val handlebars = "com.github.jknack" % "handlebars" % "4.0.6"
val circeCore = "io.circe" %% "circe-core" % circeVersion
val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
val circeGenericExtras = "io.circe" %% "circe-generic-extras" % circeVersion
val circeParser = "io.circe" %% "circe-parser" % circeVersion
val typesafeConfig = "com.typesafe" % "config" % "1.3.1"

scalaVersion := "2.12.1"
libraryDependencies ++= Seq(cats, shapeless, handlebars, circeCore, circeGeneric, circeGenericExtras, circeParser, typesafeConfig)