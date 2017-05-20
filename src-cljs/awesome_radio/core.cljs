(ns awesome-radio.core
  (:require
    [awesome-radio.components :refer [Mothership]]
    [awesome-radio.state :refer [app-state]]
    [awesome-radio.util :refer [by-id]]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; Initial State
;;------------------------------------------------------------------------------

(def initial-state
  "The initial state of our application."
  {:on? true
   :volume 5
   :band "FM"
   :frequency 94.5
   :show-edn-explorer? true})

;;------------------------------------------------------------------------------
;; Render Loop
;;------------------------------------------------------------------------------

(def app-container-el (by-id "appContainer"))

(defn- render-now!
  "Render the app on every state change."
  [_kwd _the-atom _old-state new-state]
  (rum/mount (Mothership new-state) app-container-el))

(add-watch app-state :render-loop render-now!)

;;------------------------------------------------------------------------------
;; App Initialization
;;------------------------------------------------------------------------------

;; trigger the first render
(reset! app-state initial-state)
