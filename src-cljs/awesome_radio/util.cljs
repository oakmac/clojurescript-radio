(ns awesome-radio.util)

(defn by-id [id]
  (.getElementById js/document id))
