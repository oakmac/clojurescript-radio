(ns awesome-radio.validation
  "Predicate functions."
  (:require
    [awesome-radio.constants :refer [am-frequency-interval
                                     fm-frequency-interval
                                     max-am-frequency
                                     max-fm-frequency
                                     max-volume
                                     min-am-frequency
                                     min-fm-frequency
                                     min-volume]]
    [awesome-radio.util :as util]))

;;------------------------------------------------------------------------------
;; Predicates
;;------------------------------------------------------------------------------

(defn valid-volume? [v]
  (and (number? v)
       (>= v min-volume)
       (<= v max-volume)))

(defn valid-band? [b]
  (or (= b "AM")
      (= b "FM")))

(defn valid-am-station? [f]
  (and (number? f)
       (>= f min-am-frequency)
       (<= f max-am-frequency)
       (zero? (rem f am-frequency-interval))))

(defn valid-fm-station? [f]
  (and (number? f)
       (>= f min-fm-frequency)
       (<= f max-fm-frequency)
       (odd? (* f 10))))

(defn valid-frequency? [band f]
  (and (valid-band? band)
       (if (= band "AM")
         (valid-am-station? f)
         (valid-fm-station? f))))
