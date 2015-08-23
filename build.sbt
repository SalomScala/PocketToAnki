
lazy val root = (project in file(".")).
settings(
	name := "PocketToAnki",
	version := "1.0",
	scalaVersion := "2.11.7",
	libraryDependencies ++= Seq(
  		"com.chuusai" %% "shapeless" % "2.2.5" withSources() withJavadoc(),
		"io.argonaut" %% "argonaut" % "6.1-M4"  withSources() withJavadoc(),
		"org.scalaz" %% "scalaz-core" % "7.1.3" withSources() withJavadoc(),
		"org.scalaj" %% "scalaj-http" % "1.1.5" withSources() withJavadoc())
)
resolvers ++= Seq(
	Resolver.sonatypeRepo("releases"),
	Resolver.sonatypeRepo("snapshots")
)


