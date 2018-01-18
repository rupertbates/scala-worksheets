import com.typesafe.config.ConfigFactory

val cf = ConfigFactory.parseString("foo.bar=test.test\n")
cf.getString("foo.bar")

val cf2 = ConfigFactory.parseString("blah.blah.blah=hello\n")

val merged = cf.withFallback(cf2)
