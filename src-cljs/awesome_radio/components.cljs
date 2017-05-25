(ns awesome-radio.components
  (:require
    [awesome-radio.components.state-explorer :as state-explorer]
    [awesome-radio.state :refer [app-state]]
    [awesome-radio.util :refer [js-log]]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; Toolbar
;;------------------------------------------------------------------------------

(defn- toggle-state-explorer [js-evt]
  (swap! app-state update-in [:show-state-explorer?] not)
  ;; a quick hack to make the state initially show
  (js/setTimeout #(swap! app-state identity) 20))

(rum/defc Toolbar < rum/static
  []
  [:div.toolbar
    [:button {:on-click toggle-state-explorer} "Toggle State Explorer"]])

;;------------------------------------------------------------------------------
;; Radio
;;------------------------------------------------------------------------------

(rum/defc VolumeBar < rum/static
  [state]
  [:div.row
    "TODO: station"])


(rum/defc StationAndVolume < rum/static
  [band frequency volume]
  [:div.row
    [:div.box
      [:label "Station:"]
      [:div.seven-segment (str band " " frequency)]]
    [:div.box
      [:label "Volume:"]
      [:div.seven-segment (str volume)]]])


(rum/defc Radio < rum/static
  [{:keys [band frequency volume]}]
  [:div.radio
    (StationAndVolume band frequency volume)])


;;------------------------------------------------------------------------------
;; Top Level Component
;;------------------------------------------------------------------------------

(rum/defc Mothership < rum/static
  [state]
  [:div.mothership
    [:div.main-container
      (Toolbar)
      [:div.radio-container
        (Radio state)]]
    (when (:show-state-explorer? state)
      (state-explorer/StateExplorer state))])
