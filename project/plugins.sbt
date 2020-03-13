resolvers += Resolver.url("HMRC Sbt Plugin Releases", 
                          url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("io.gatling"  % "gatling-sbt"        % "2.2.2")
