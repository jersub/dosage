(defproject dosage "0.1.0-SNAPSHOT"
  :url "https://github.com/jersub/dosage"
  :license {:name "AGPL-3.0"
            :url "http://www.gnu.org/licenses/agpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot dosage.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
