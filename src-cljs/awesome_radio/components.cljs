(ns awesome-radio.components
  (:require
    [awesome-radio.state :refer [app-state]]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; Radio
;;------------------------------------------------------------------------------

(rum/defc Radio < rum/static
  [state]
  [:div.radio-container
    "TODO: radio"])

;;------------------------------------------------------------------------------
;; EDN Viewer
;;------------------------------------------------------------------------------

(rum/defc EDNViewer < rum/static
  [state]
  [:div.edn-viewer-container
    [:h2 "Application State"]
    [:textarea (-> state
                   clj->js
                   (js/JSON.stringify nil 2))]])

;;------------------------------------------------------------------------------
;; Top Level Component
;;------------------------------------------------------------------------------

(rum/defc Mothership < rum/static
  [state]
  [:div.mothership
    (Radio state)
    (EDNViewer state)])
