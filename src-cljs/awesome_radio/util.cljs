(ns awesome-radio.util)

(defn js-log [js-thing]
  (.log js/console js-thing))

(defn by-id [id]
  (.getElementById js/document id))
