(defproject linearprogramming "0.1.0-SNAPSHOT"
  :description "Linear programming library"
  :url "https://github.com/arbbakbenny/linearprogramming"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [uncomplicate/commons "0.8.0"]
                 [uncomplicate/neanderthal "0.25.6"]
                 [uncomplicate/clojurecuda "0.7.1"]]
  :repl-options {:init-ns linearprogramming.core}
  :jvm-opts ^:replace [#_"--add-opens=java.base/jdk.internal.ref=ALL-UNNAMED"]))
