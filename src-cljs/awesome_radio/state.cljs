(ns awesome-radio.state
  "Holds our application state atom.")

(def initial-state
  "The initial state of our application."
  {:band "FM"
   :frequency 94.5
   :on? true
   :show-state-explorer? true
   :state-explorer-format "EDN"
   :volume 5})

(def app-state (atom initial-state))
