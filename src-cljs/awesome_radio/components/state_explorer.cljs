(ns awesome-radio.components.state-explorer
  (:require
    [cljs.reader :as reader]
    [awesome-radio.state :refer [*app-state]]
    [awesome-radio.util :as util]
    [rum.core :as rum]))

;;------------------------------------------------------------------------------
;; State Explorer
;;------------------------------------------------------------------------------

(defn- change-explorer-format [new-format js-evt]
  (swap! *app-state assoc :state-explorer-format new-format))


(defn- blur-state-textarea [js-evt]
  (let [new-state-txt (aget js-evt "currentTarget" "value")
        edn? (= (:state-explorer-format @*app-state) "EDN")
        format-fn (if edn? reader/read-string js/JSON.parse)
        new-value (try
                    (format-fn new-state-txt)
                    (catch :default e false))
        new-value (if (map? new-value)
                    new-value
                    (js->clj new-value :keywordize-keys true))]
    (when new-value
      (reset! *app-state new-value))))


(rum/defc StateExplorer < rum/static
  [state]
  (let [edn? (= (:state-explorer-format state) "EDN")]
    [:div.state-viewer-container
      [:h2 "Application State"]
      [:div.radios
        [:label
          [:input {:type "radio"
                   :checked edn?
                   :on-click (partial change-explorer-format "EDN")}]
          " EDN"]
        [:label
          [:input {:type "radio"
                   :checked (not edn?)
                   :on-click (partial change-explorer-format "JSON")}]
          " JSON"]]
      [:textarea#stateTextarea
        {:on-blur blur-state-textarea}]]))
