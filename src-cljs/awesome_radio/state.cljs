(ns awesome-radio.state
  "Holds our application state atom."
  (:require
    [awesome-radio.constants :refer [krbe
                                     sports-radio-610
                                     the-buzz
                                     ktru]]
    [awesome-radio.validation :refer [valid-am-station?
                                      valid-band?
                                      valid-fm-station?
                                      valid-frequency?
                                      valid-volume?]]
    [awesome-radio.util :as util]))

;;------------------------------------------------------------------------------
;; Initial State
;;------------------------------------------------------------------------------

(def initial-state
  "The initial state of our application."
  {:band "FM"
   :favorites [the-buzz krbe sports-radio-610 ktru nil nil]
   :frequency 96.5
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

; (defn- valid-state? [s])

; (set-validator! *app-state valid-state?)
