(ns awesome-radio.state
  "Holds our application state atom."
  (:require
    [awesome-radio.validation :refer [valid-am-station?
                                      valid-band?
                                      valid-fm-station?
                                      valid-volume?]]))

;;------------------------------------------------------------------------------
;; Initial State
;;------------------------------------------------------------------------------

(def initial-state
  "The initial state of our application."
  {:band "FM"
   :favorites [nil nil nil nil nil nil]
   :frequency 94.5
   :on? true
   :show-state-explorer? true
   :state-explorer-format "EDN"
   :volume 5})

;;------------------------------------------------------------------------------
;; The Atom
;;------------------------------------------------------------------------------

(def *app-state (atom initial-state))

;;------------------------------------------------------------------------------
;; Validation
;;------------------------------------------------------------------------------

(defn- valid-state? [s]
  (and (map? s)
       (valid-volume? (:volume s))
       (valid-band? (:band s))))

(set-validator! *app-state valid-state?)
