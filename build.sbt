resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)


val circeVersion = "0.7.0"

val cats = "org.typelevel" %% "cats" % "0.9.0"
val shapeless = "com.chuusai" %% "shapeless" % "2.3.2"
val handlebars = "com.github.jknack" % "handlebars" % "4.0.6"
val circeCore = "io.circe" %% "circe-core" % circeVersion
val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
val circeGenericExtras = "io.circe" %% "circe-generic-extras" % circeVersion
val circeParser = "io.circe" %% "circe-parser" % circeVersion
scalaVersion := "2.12.1"
libraryDependencies ++= Seq(cats, shapeless, handlebars, circeCore, circeGeneric, circeGenericExtras, circeParser)