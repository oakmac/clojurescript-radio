(defproject awesome-radio "0.1.0"

  :description "An awesome radio written in ClojureScript."

  :url "https://github.com/oakmac/clojurescript-radio/"
  :license {:name "ISC License"
            :url "https://github.com/oakmac/clojurescript-radio/blob/master/LICENSE.md"
            :distribution :repo}

  :dependencies
    [[org.clojure/clojure "1.9.0-alpha14"]
     [org.clojure/clojurescript "1.10.63"]
     [fipp "0.6.8"]
     [hiccups "0.3.0"]
     [rum "0.10.8"]]

  :plugins [[lein-cljsbuild "1.1.6"]]

  :clean-targets ["public/js/app.dev.js"
                  "public/js/app.prod.js"]

  :cljsbuild
    {:builds
      [{:id "dev"
        :source-paths ["src-cljs"]
        :compiler {:output-to "public/js/app.dev.js"
                   :optimizations :whitespace}}

       {:id "prod"
        :source-paths ["src-cljs"]
        :compiler {:output-to "public/js/app.prod.js"
                   :optimizations :advanced
                   :pretty-print false}}]})
