(ns awesome-radio.components
  (:require
    [awesome-radio.constants :refer [min-volume max-volume]]
    [awesome-radio.components.state-explorer :as state-explorer]
    [awesome-radio.state :refer [*app-state]]
    [awesome-radio.util :as util]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; App Toolbar
;;------------------------------------------------------------------------------

(defn- toggle-state-explorer [js-evt]
  (swap! *app-state update-in [:show-state-explorer?] not)
  ;; a quick hack to make the state initially show
  (js/setTimeout #(swap! *app-state identity) 10))

(rum/defc Toolbar < rum/static
  []
  [:div.toolbar
    [:button {:on-click toggle-state-explorer} "Toggle State Explorer"]])

;;------------------------------------------------------------------------------
;; Station and Volume Display
;;------------------------------------------------------------------------------

(rum/defc StationAndVolume < rum/static
  [band frequency volume]
  [:div.row
    [:div.box
      [:label "Station:"]
      [:div.seven-segment (str band " " frequency)]]
    [:div.box
      [:label "Volume:"]
      [:div.seven-segment (str volume)]]])

;;------------------------------------------------------------------------------
;; Tuner and Volume Controls
;;------------------------------------------------------------------------------

(defn- click-source-btn [new-source _js-evt]
  (swap! *app-state assoc :band new-source))


(defn- click-tune-up-btn [_js-evt])
  ;; TODO: write me


(defn- click-tune-down-btn [_js-evt])
  ;; TODO: write me


(defn- click-volume-up-btn
  "Turn it up my man!"
  [_js-evt])
  ;; TODO: write me


(defn- click-volume-down-btn
  "Turn it down you kids!"
  [_js-evt])
  ;; TODO: write me


(defn- change-volume [js-evt]
  (let [new-volume (int (aget js-evt "currentTarget" "value"))]
    (swap! *app-state assoc :volume new-volume)))


(rum/defc TunerAndVolumeControls < rum/static
  [volume]
  [:div.row
    [:div.box
      [:label "Source:"]
      [:div
        [:button {:on-click (partial click-source-btn "AM")} "AM"]
        [:button {:on-click (partial click-source-btn "FM")} "FM"]]]
    [:div.box
      [:label "Tune:"]
      [:div
        [:button {:on-click click-tune-up-btn} "▲"]
        [:button {:on-click click-tune-down-btn} "▼"]]]
    [:div.box
      [:label "Volume:"]
      [:div
        ;; TOOD: maybe we want buttons instead?
        ; [:button {:on-click click-volume-up-btn} "▲"]
        ; [:button {:on-click click-volume-down-btn} "▼"]
        [:input {:max max-volume
                 :min min-volume
                 :on-change change-volume
                 :type "range"
                 :value volume}]]]])

;;------------------------------------------------------------------------------
;; Favorites
;;------------------------------------------------------------------------------

(defn- click-favorite-btn [{:keys [band frequency]}]
  (swap! *app-state assoc :band band
                          :frequency frequency))

(rum/defc FavoriteButton < rum/static
                           util/index-key-mixin
  [idx favorite]
  [:button.favorite-btn
    {:on-click (partial click-favorite-btn favorite)}
    (str (inc idx))])

(rum/defc Favorites < rum/static
  [favorites]
  [:div.row
    [:div.box
      [:label "Favorites:"]
      [:div
        (map-indexed FavoriteButton favorites)]]])

;;------------------------------------------------------------------------------
;; Radio
;;------------------------------------------------------------------------------

(rum/defc Radio < rum/static
  [{:keys [band favorites frequency volume]}]
  [:div.radio
    (StationAndVolume band frequency volume)
    (TunerAndVolumeControls volume)
    (Favorites favorites)])

;;------------------------------------------------------------------------------
;; Top Level App Component
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
