(ns awesome-radio.core
  (:require
    [awesome-radio.components :refer [Mothership]]
    [awesome-radio.state :refer [*app-state]]
    [awesome-radio.util :as util]
    [fipp.edn]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; State Explorer
;;------------------------------------------------------------------------------

(defn- update-state-explorer!
  "Update the State Explorer textarea if it is showing."
  [_kwd _the-atom _old-state new-state]
  (let [textarea-el (util/by-id "stateTextarea")
        edn? (= (:state-explorer-format new-state) "EDN")]
    (when textarea-el
      (aset textarea-el "value"
        (if edn?
          (-> new-state fipp.edn/pprint with-out-str)
          (-> new-state clj->js (js/JSON.stringify nil 2)))))))

(add-watch *app-state :state-explorer update-state-explorer!)

;;------------------------------------------------------------------------------
;; Render Loop
;;------------------------------------------------------------------------------

(def app-container-el (util/by-id "appContainer"))

(defn- render!
  "Render the app on every state change."
  [_kwd _the-atom _old-state new-state]
  (rum/mount (Mothership new-state) app-container-el))

(add-watch *app-state :render-loop render!)

;;------------------------------------------------------------------------------
;; App Initialization
;;------------------------------------------------------------------------------

;; trigger the first render
(swap! *app-state identity)
