(defproject clojure-se "0.1.0-SNAPSHOT"
  :description "A clojure Helidon SE app"
  :dependencies [[org.clojure/clojure "1.9.0"]
               [io.helidon/helidon-bom "1.0.0" :extension "pom"]
               [io.helidon.bundles/helidon-bundles-webserver "1.0.0"]
               [io.helidon.config/helidon-config-yaml "1.0.0"]
               [io.helidon.health/helidon-health "1.0.0"]
               [io.helidon.health/helidon-health-checks "1.0.0"]
               [io.helidon.metrics/helidon-metrics "1.0.0"]]  :main ^:skip-aot clojure-se.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
