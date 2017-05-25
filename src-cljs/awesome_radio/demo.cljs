(ns awesome-radio.demo
  (:require-macros
    [hiccups.core :as hiccups])
  (:require
    [awesome-radio.util :as util]
    [hiccups.runtime :as hiccupsrt]
    [sablono.core :as sablono]))

;;------------------------------------------------------------------------------
;; Same syntax can produce HTML string or React Components (or anything!)
;;------------------------------------------------------------------------------

(defn- table-row [row]
  [:tr
    [:td (nth row 0)]
    [:td (nth row 1)]
    [:td (nth row 2)]])

(defn- create-table [cols rows]
  [:table#awesometable.compressed.striped
    [:thead
      [:tr
        (map
          (fn [txt] [:th txt])
          cols)]]
    [:tbody
      (map table-row rows)]])

(def tx-city-cols
  ["Rank" "City" "Awesome Level"])

(def tx-city-rows
  [["1" "Houston" "Most Awesome"]
   ["2" "Austin" "Pretty great, but not Houston"]
   ["2" "Dallas" "No comment"]])

(def tx-cities-table (create-table tx-city-cols tx-city-rows))

; (util/js-log "An HTML string:")
; (util/js-log (hiccups/html tx-cities-table))
; (util/js-log "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
; (util/js-log "A React component:")
; (util/js-log (sablono/html tx-cities-table))
