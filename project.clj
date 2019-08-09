(defproject clojure-se "0.1.0-SNAPSHOT"
  :description "A clojure Helidon SE app"
  :dependencies [[org.clojure/clojure "1.10.0"]
               [io.helidon/helidon-bom "1.2.0" :extension "pom"]
               [io.helidon.bundles/helidon-bundles-webserver "1.1.2"]
               [io.helidon.config/helidon-config-yaml "1.1.2"]
               [io.helidon.health/helidon-health "1.1.2"]
               [io.helidon.health/helidon-health-checks "1.1.2"]
               [io.helidon.metrics/helidon-metrics "1.1.2"]]  :main ^:skip-aot clojure-se.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
