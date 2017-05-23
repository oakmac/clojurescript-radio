(ns awesome-radio.components
  (:require
    [awesome-radio.state :refer [app-state]]
    [awesome-radio.util :refer [js-log]]
    [fipp.edn]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; Radio
;;------------------------------------------------------------------------------

(defn- click-btn [js-evt]
  (swap! app-state update-in [:show-state-explorer?] not))

(rum/defc Radio < rum/static
  [state]
  [:div.radio-container
    "TODO: radio"
    [:button {:on-click click-btn} "Toggle EDN Viewer"]])

;;------------------------------------------------------------------------------
;; State Viewer
;;------------------------------------------------------------------------------

(defn- change-explorer-format [new-format js-evt]
  (swap! app-state assoc :state-explorer-format new-format))

(defn- blur-state-textarea [js-evt]
  (let [new-state-txt (aget js-evt "currentTarget" "value")]
    (js-log new-state-txt)))

(rum/defc EDNViewer < rum/static
  [state]
  (let [edn? (= (:state-explorer-format state) :edn)]
    [:div.state-viewer-container
      [:h2 "Application State"]
      [:div.radios
        [:label
          [:input {:type "radio"
                   :checked edn?
                   :on-click (partial change-explorer-format :edn)}]
          "EDN"]
        [:label
          [:input {:type "radio"
                   :checked (not edn?)
                   :on-click (partial change-explorer-format :json)}]
          "JSON"]]
      [:textarea
        {:on-blur blur-state-textarea
         :value
          (if edn?
            (-> state fipp.edn/pprint with-out-str)
            (-> state clj->js (js/JSON.stringify nil 2)))}]]))

;;------------------------------------------------------------------------------
;; Top Level Component
;;------------------------------------------------------------------------------

(rum/defc Mothership < rum/static
  [state]
  [:div.mothership
    (Radio state)
    (when (:show-state-explorer? state)
      (EDNViewer state))])
